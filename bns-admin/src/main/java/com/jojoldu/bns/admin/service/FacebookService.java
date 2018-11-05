package com.jojoldu.bns.admin.service;

import com.jojoldu.bns.admin.service.dto.facebook.FacebookAccessToken;
import com.jojoldu.bns.admin.service.dto.facebook.FacebookPageAccessToken;
import com.jojoldu.bns.core.domain.member.Member;
import com.jojoldu.bns.core.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jojoldu@gmail.com on 2018. 11. 2.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class FacebookService {

    private final MemberRepository memberRepository;
    private final FacebookRestTemplate facebookRestTemplate;

    @Transactional
    public void saveToken(String code, String email) {
        FacebookAccessToken accessToken = facebookRestTemplate.requestAccessToken(code);
        FacebookPageAccessToken pageAccessToken = facebookRestTemplate.exchangePageToken(accessToken);
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("가입된 유저가 아닙니다."));
        member.addFacebookPage(pageAccessToken.toEntities());
    }

    @Transactional
    public void post(String pageId, String accessToken, String message) {
        facebookRestTemplate.postFeed(pageId, accessToken, message);
    }

}
