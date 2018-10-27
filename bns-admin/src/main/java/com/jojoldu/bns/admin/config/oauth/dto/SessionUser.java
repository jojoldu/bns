package com.jojoldu.bns.admin.config.oauth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import java.io.Serializable;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 27.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@NoArgsConstructor
public class SessionUser implements Serializable {

    public static final String SESSION_KEY = "user";
    public static final SessionUser EMPTY = new SessionUser();

    private String accessToken;
    private String name;    // 본명
    private String email;

    @Builder
    public SessionUser(@Nonnull String accessToken, @Nonnull String name, @Nonnull String email) {
        this.accessToken = accessToken;
        this.name = name;
        this.email = email;
    }
}
