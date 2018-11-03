package com.jojoldu.bns.admin.service;

import com.jojoldu.bns.admin.service.dto.bitly.BitlyLinkExchangeDto;
import com.jojoldu.bns.admin.web.api.dto.BitlyLinkRequestDto;
import com.jojoldu.bns.core.domain.link.OriginLink;
import com.jojoldu.bns.core.domain.link.SnsLink;
import com.jojoldu.bns.core.domain.member.Member;
import com.jojoldu.bns.core.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    private final MemberRepository memberRepository;

    public List<String> createBitlyLink(BitlyLinkRequestDto requestDto, String guid, String accessToken, String email) {
        List<BitlyLinkExchangeDto> exchangeDtos = requestDto.toExchangeDtoList(guid);
        OriginLink originLink = requestDto.toOriginLink();
        List<SnsLink> snsLinks = exchangeDtos.stream()
                .map(e -> bitlyRestTemplate.create(accessToken, e).toSnsLink(e.getSnsType()))
                .collect(Collectors.toList());

        for (SnsLink snsLink : snsLinks) {
            originLink.addSnsLink(snsLink);
        }

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        member.addOriginLink(originLink);


        return snsLinks.stream()
                .map(SnsLink::getLink)
                .collect(Collectors.toList());
    }
}
