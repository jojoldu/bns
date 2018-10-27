package com.jojoldu.bns.admin.config.oauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jojoldu.bns.admin.domain.Member;
import com.jojoldu.bns.admin.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.List;
import java.util.Map;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 27.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitlyAuthDto {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private String login;
    private String name;
    private List<BitlyEmail> emails;
    private String accessToken;

    @Builder
    private BitlyAuthDto(String login, String name, List<BitlyEmail> emails, String accessToken) {
        this.login = login;
        this.name = name;
        this.emails = emails;
        this.accessToken = accessToken;
    }

    public static BitlyAuthDto of (Authentication authentication) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        Object details = oAuth2Authentication.getUserAuthentication().getDetails();

        BitlyAuthDto bitlyAuthDto = MAPPER.convertValue(details, BitlyAuthDto.class);
        bitlyAuthDto.setAccessToken(extractAccessToken(authentication));

        return bitlyAuthDto;
    }

    private static String extractAccessToken(Authentication authentication) {
        Map<String, String> details = MAPPER.convertValue(authentication.getDetails(), Map.class);
        return details.get("tokenValue");
    }

    public String getEmail(){
        return emails.stream()
                .filter(e -> e.isPrimary && e.isVerified)
                .map(BitlyEmail::getEmail)
                .findFirst()
                .orElseGet(() -> emails.get(0).getEmail());
    }

    public Member toNewEntity() {
        return Member.builder()
                .accessToken(accessToken)
                .email(getEmail())
                .name(name)
                .username(login)
                .role(Role.GUEST)
                .build();
    }

    public SessionUser toSessionDto() {
        return SessionUser.builder()
                .accessToken(accessToken)
                .email(getEmail())
                .name(name)
                .build();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class BitlyEmail {
        private String email;

        @JsonProperty("is_primary")
        private boolean isPrimary;

        @JsonProperty("is_verified")
        private boolean isVerified;
    }
}
