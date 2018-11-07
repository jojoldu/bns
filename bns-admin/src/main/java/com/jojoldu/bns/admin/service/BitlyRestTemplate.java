package com.jojoldu.bns.admin.service;

import com.jojoldu.bns.admin.service.dto.bitly.BitlyCreateResponseDto;
import com.jojoldu.bns.admin.service.dto.bitly.BitlyGroupDto;
import com.jojoldu.bns.admin.service.dto.bitly.BitlyLinkExchangeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * Created by jojoldu@gmail.com on 2018. 11. 2.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class BitlyRestTemplate {
    private static final String GROUP_URL = "https://api-ssl.Bitly.com/v4/groups";
    private final RestTemplate restTemplate;

    public String getGroupGuid(String accessToken) {
        HttpEntity request = new HttpEntity<>(createAuthHeaders(accessToken));
        try {
            ResponseEntity<BitlyGroupDto> response = restTemplate.exchange(GROUP_URL, HttpMethod.GET, request, BitlyGroupDto.class);
            return Objects.requireNonNull(response.getBody()).getGroupGuid();
        } catch (Exception e) {
            log.error("Bitly Group Guid 가져오기가 실패했습니다. message={}", e.getMessage(), e);
            throw new IllegalArgumentException("Bitly Group Guid 가져오기가 실패했습니다. ", e);
        }
    }

    public BitlyCreateResponseDto create(String accessToken, BitlyLinkExchangeDto dto) {
        HttpEntity<BitlyLinkExchangeDto> request = new HttpEntity<>(dto, createAuthHeaders(accessToken));
        try {
            ResponseEntity<BitlyCreateResponseDto> response = restTemplate.exchange(GROUP_URL, HttpMethod.POST, request, BitlyCreateResponseDto.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("Bitly 링크 생성이 실패했습니다. requestDto={}, message={}", dto, e.getMessage(), e);
            throw new IllegalArgumentException("Bitly 링크 생성이 실패했습니다.", e);
        }
    }

    HttpHeaders createAuthHeaders(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders() {
        };
        httpHeaders.set("Authorization", "Bearer " + accessToken);
        return httpHeaders;
    }
}
