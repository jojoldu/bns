package com.jojoldu.bns.admin.config.oauth;

import com.jojoldu.bns.admin.config.oauth.dto.BitlyAuthDto;
import com.jojoldu.bns.admin.config.oauth.dto.SessionUser;
import com.jojoldu.bns.admin.service.BitlyRestTemplate;
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
public class BitlyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;
    private final BitlyRestTemplate bitlyRestTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        BitlyAuthDto authDto = BitlyAuthDto.of(authentication);
        String guid = bitlyRestTemplate.getGroupGuid(authDto.getAccessToken());
        saveOrRefresh(authDto, guid);
        httpSession.setAttribute(SessionUser.SESSION_KEY, authDto.toSessionDto(guid));
        response.sendRedirect("/");
    }

    private void saveOrRefresh(BitlyAuthDto dto, String guid) {
        Optional<Member> optional = memberRepository.findByUsername(dto.getLogin());

        if(optional.isPresent()){
            Member entity = optional.get();
            entity.refreshToken(dto.getAccessToken());
            memberRepository.save(entity);
        } else {
            memberRepository.save(dto.toNewEntity(guid));
        }
    }
}
