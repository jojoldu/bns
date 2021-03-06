package com.jojoldu.bns.admin.config.oauth;

import com.jojoldu.bns.core.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import javax.servlet.Filter;

import static java.util.Collections.singletonList;
/**
 * Created by jojoldu@gmail.com on 2018. 10. 27.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@RequiredArgsConstructor
@Configuration
@EnableOAuth2Client
public class OAuthConfig {

    private final OAuth2ClientContext oauth2ClientContext;
    private final BitlyAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final MemberRepository memberRepository;

    @Bean
    public Filter ssoFilter() {
        OAuth2ClientAuthenticationProcessingFilter bitlyFilter = new OAuth2ClientAuthenticationProcessingFilter("/login");
        bitlyFilter.setRestTemplate(new OAuth2RestTemplate(bitlyClient(), oauth2ClientContext));
        UserInfoTokenServices bitlyTokenServices = new UserInfoTokenServices(bitlyResource().getUserInfoUri(), bitlyClient().getClientId());
        bitlyTokenServices.setAuthoritiesExtractor(authoritiesExtractor());
        bitlyFilter.setTokenServices(bitlyTokenServices);
        bitlyFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        return bitlyFilter;
    }

    /**
     * 권한 할당
     */
    @Bean
    public AuthoritiesExtractor authoritiesExtractor() {
        return map -> {
            String username = (String) map.get("login");
            GrantedAuthority authority = memberRepository.findByUsername(username)
                    .map(b -> MemberAuthority.getAuthority(b.getRole()))
                    .orElse(MemberAuthority.GUEST_AUTHORITY);
            return singletonList(authority);
        };
    }

    @Bean
    @ConfigurationProperties("facebook.client")
    public AuthorizationCodeResourceDetails facebookClient() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("facebook.resource")
    public ResourceServerProperties facebookResource() {
        return new ResourceServerProperties();
    }

    @Bean
    @ConfigurationProperties("bitly.client")
    public OAuth2ProtectedResourceDetails bitlyClient() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("bitly.resource")
    public ResourceServerProperties bitlyResource() {
        return new ResourceServerProperties();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }
}
