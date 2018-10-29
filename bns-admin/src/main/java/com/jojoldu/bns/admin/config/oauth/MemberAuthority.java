package com.jojoldu.bns.admin.config.oauth;

import com.jojoldu.bns.core.domain.member.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 29.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

public class MemberAuthority {
    public static GrantedAuthority GUEST_AUTHORITY = new SimpleGrantedAuthority(Role.GUEST.getKey());

    public static GrantedAuthority getAuthority(Role role) {
        return new SimpleGrantedAuthority(role.getKey());
    }
}
