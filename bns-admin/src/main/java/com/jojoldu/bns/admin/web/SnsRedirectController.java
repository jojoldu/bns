package com.jojoldu.bns.admin.web;

import com.jojoldu.bns.admin.web.dto.FacebookAccessToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 31.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@RequiredArgsConstructor
@Controller
public class SnsRedirectController {
    private final RestTemplate restTemplate;

    @Autowired
    @Qualifier("facebookClient")
    private AuthorizationCodeResourceDetails facebookClient;

    @GetMapping("/accessToken/facebook")
    public String getFacebookSignIn(String code) throws Exception {
        requestAccessToken(code);

        return "redirect:content.html";
    }

    private void requestAccessToken(String code) throws Exception {
        String url = "https://graph.facebook.com/v3.2/oauth/access_token?" +
                "client_id=" + facebookClient.getClientId() +
                "&redirect_uri=" + "https://localhost:8443/content.html" +
                "&client_secret=" + facebookClient.getClientSecret() +
                "&code=" + code;

        ResponseEntity<FacebookAccessToken> responseEntity = restTemplate.getForEntity(url, FacebookAccessToken.class);
        log.info(responseEntity.getBody().toString());
    }
}
