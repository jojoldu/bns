package com.jojoldu.bns.core.domain.link;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 28.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@RequiredArgsConstructor
public enum SnsType {

    FACEBOOK("페이스북"),
    TWITTER("트위터"),
    TELEGRAM("텔레그렘"),
    LINE("라인");

    private final String title;

}
