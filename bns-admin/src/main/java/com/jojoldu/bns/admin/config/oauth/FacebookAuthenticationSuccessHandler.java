package com.jojoldu.bns.admin.config.oauth;

import com.jojoldu.bns.admin.config.oauth.dto.BitlyAuthDto;
import com.jojoldu.bns.admin.config.oauth.dto.SessionUser;
import com.jojoldu.bns.core.domain.member.FacebookRepository;
import com.jojoldu.bns.core.domain.member.Member;
import com.jojoldu.bns.core.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 27.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@RequiredArgsConstructor
@Component
public class FacebookAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final FacebookRepository facebookRepository;
    private final HttpSession httpSession;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Member member = findMember();

        BitlyAuthDto authDto = BitlyAuthDto.of(authentication);
        saveOrRefresh(authDto);
        httpSession.setAttribute(SessionUser.SESSION_KEY, authDto.toSessionDto());
        response.sendRedirect("/");
    }

    private Member findMember() {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute(SessionUser.SESSION_KEY);
        return memberRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 유저로 요청중입니다."));
    }

    private void saveOrRefresh(BitlyAuthDto dto) {
        Optional<Member> optional = memberRepository.findByUsername(dto.getLogin());

        if (optional.isPresent()) {
            Member entity = optional.get();
            entity.refreshToken(dto.getAccessToken());
            memberRepository.save(entity);
        } else {
            memberRepository.save(dto.toNewEntity());
        }
    }
}
