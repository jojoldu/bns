package com.jojoldu.bns.admin.config.oauth;

import com.jojoldu.bns.admin.config.oauth.dto.BitlyUserDetails;
import com.jojoldu.bns.core.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
/**
 * Created by jojoldu@gmail.com on 2018. 10. 27.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@RequiredArgsConstructor
@Component
public class BitlyUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return memberRepository.findByUsername(username)
                .map(BitlyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. username: "+username));
    }
}
