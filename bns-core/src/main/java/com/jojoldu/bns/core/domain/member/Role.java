package com.jojoldu.bns.core.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 27.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@AllArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "관리자"),
    SYSTEM("ROLE_SYSTEM", "시스템");

    private String key;
    private String title;


}
