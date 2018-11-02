package com.jojoldu.bns.admin.service;

import com.jojoldu.bns.admin.service.dto.bitly.BitlyCreateResponseDto;
import com.jojoldu.bns.admin.service.dto.bitly.BitlyLinkExchangeDto;
import com.jojoldu.bns.admin.web.api.dto.BitlyLinkRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2018. 11. 2.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class BitlyService {
    private final BitlyRestTemplate bitlyRestTemplate;


    public List<String> createBitlyLink(BitlyLinkRequestDto requestDto, String guid, String accessToken) {
        List<BitlyLinkExchangeDto> exchangeDtos = requestDto.toExchangeDtoList(guid);
        List<String> resultLinks = new ArrayList<>();
        for (BitlyLinkExchangeDto exchangeDto : exchangeDtos) {
            BitlyCreateResponseDto bitly = bitlyRestTemplate.create(accessToken, exchangeDto);
            resultLinks.add(bitly.getLink());
        }

        return resultLinks;
    }
}
