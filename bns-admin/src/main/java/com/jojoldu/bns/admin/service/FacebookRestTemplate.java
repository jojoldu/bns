package com.jojoldu.bns.admin.service;

import com.jojoldu.bns.admin.service.dto.facebook.FacebookAccessToken;
import com.jojoldu.bns.admin.service.dto.facebook.FacebookPageAccessToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jojoldu@gmail.com on 2018. 11. 2.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class FacebookRestTemplate {

    private final RestTemplate restTemplate;

    @Autowired
    @Qualifier("facebookClient")
    private AuthorizationCodeResourceDetails facebookClient;

    public FacebookAccessToken requestAccessToken(String code) {
        String url = "https://graph.facebook.com/v3.2/oauth/access_token?" +
                "client_id=" + facebookClient.getClientId() +
                "&redirect_uri=" + "https://localhost:8443/accessToken/facebook" +
                "&client_secret=" + facebookClient.getClientSecret() +
                "&code=" + code;

        try {
            ResponseEntity<FacebookAccessToken> responseEntity = restTemplate.getForEntity(url, FacebookAccessToken.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error(((HttpClientErrorException) e).getResponseBodyAsString());
            log.error("Request Facebook Access Token", e);
            throw new IllegalArgumentException("Request Facebook Access Token", e);
        }
    }

    public FacebookPageAccessToken exchangePageToken(FacebookAccessToken accessToken) {
        String url = "https://graph.facebook.com/me/accounts?access_token=" + accessToken.getAccessToken();

        try {
            ResponseEntity<FacebookPageAccessToken> responseEntity = restTemplate.getForEntity(url, FacebookPageAccessToken.class);

            return responseEntity.getBody();
        } catch (Exception e) {
            log.error(((HttpClientErrorException) e).getResponseBodyAsString());
            log.error("Request Facebook Page Access Token", e.getCause());
            throw new IllegalArgumentException("Request Facebook Page Access Token", e);
        }
    }

    public String postFeed(String pageId, String accessToken, String message) {
        String url = String.format("https://graph.facebook.com/v3.2/%s/feed?access_token=%s&message=%s", pageId, accessToken, message);

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

            return responseEntity.getBody();
        } catch (Exception e) {
            log.error(((HttpClientErrorException) e).getResponseBodyAsString());
            log.error("Request Facebook Page Post", e.getCause());
            throw new IllegalArgumentException("Request Facebook Page Post", e);
        }
    }
}
