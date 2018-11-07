package com.jojoldu.bns.admin.service;

import com.jojoldu.bns.admin.service.dto.bitly.BitlyLinkRequestDto;
import com.jojoldu.bns.admin.service.dto.bitly.SendSnsRequestDto;
import com.jojoldu.bns.core.domain.link.OriginLink;
import com.jojoldu.bns.core.domain.link.OriginLinkRepository;
import com.jojoldu.bns.core.domain.link.SnsLink;
import com.jojoldu.bns.core.domain.link.SnsType;
import com.jojoldu.bns.core.domain.member.Member;
import com.jojoldu.bns.core.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final OriginLinkRepository originLinkRepository;
    private final FacebookService facebookService;

    @Transactional
    public List<String> createBitlyLink(BitlyLinkRequestDto requestDto, String guid, String accessToken, String email) {
        OriginLink originLink = requestDto.toOriginLink();
        List<SnsLink> snsLinks = getSnsLinks(requestDto, guid, accessToken);
        originLink.addSnsLinks(snsLinks);
        addOriginLinkToMember(email, originLink);

        return snsLinks.stream()
                .map(SnsLink::getLink)
                .collect(Collectors.toList());
    }

    @Transactional
    public void sendAll(SendSnsRequestDto requestDto, String email) {
        /**
         * 1. Member 조회
         * 2. 원본 링크와 SNS 링크 조회
         * 3. 각 SNS restTemplate로 메세지 발송
         */

        Member member = getMember(email);
        OriginLink originLink = getOriginLink(requestDto);
        sendAllFacebook(requestDto, originLink);

    }

    void sendAllFacebook(SendSnsRequestDto requestDto, OriginLink originLink) {
        SnsLink facebookLink = originLink.findSnsLink(SnsType.FACEBOOK);
        List<String> facebookPageIds = requestDto.getFacebookPageIds();
        facebookService.sendAll(facebookLink, facebookPageIds);
    }

    OriginLink getOriginLink(SendSnsRequestDto requestDto) {
        return originLinkRepository.findById(requestDto.getOriginLinkId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 링크입니다."));
    }

    void addOriginLinkToMember(String email, OriginLink originLink) {
        Member member = getMember(email);
        member.addOriginLink(originLink);
    }

    Member getMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
    }

    List<SnsLink> getSnsLinks(BitlyLinkRequestDto requestDto, String guid, String accessToken) {
        return requestDto.toExchangeDtoList(guid).stream()
                .map(e -> bitlyRestTemplate.create(accessToken, e).toSnsLink(e.getSnsType()))
                .collect(Collectors.toList());
    }
}
