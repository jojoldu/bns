package com.jojoldu.bns.admin.service;

import com.jojoldu.bns.admin.config.oauth.dto.TelegramProperties;
import com.jojoldu.bns.admin.service.dto.telegram.TelegramRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jojoldu@gmail.com on 2018. 11. 9.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class TelegramRestTemplate {
    private final RestTemplate restTemplate;
    private final TelegramProperties telegramProperties;

    public String sendAll(TelegramRequestDto requestDto) {
        HttpEntity<TelegramRequestDto> request = new HttpEntity<>(requestDto, createAuthHeaders(telegramProperties.getApiKey()));
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(telegramProperties.getUrl(), HttpMethod.POST, request, String.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            log.error(((HttpClientErrorException) e).getResponseBodyAsString());
            log.error("Request Telegram Send All", e);
            throw new IllegalArgumentException("Request Telegram Send All", e);
        }
    }

    HttpHeaders createAuthHeaders(String apiKey) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-api-key", apiKey);
        httpHeaders.set("Content-Type", "application/json");

        return httpHeaders;
    }
}
