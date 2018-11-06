package com.jojoldu.bns.admin.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by jojoldu@gmail.com on 2018. 11. 6.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@AllArgsConstructor
public enum ResponseCode {
    OK("요청이 성공하였습니다."),

    BAD_PARAMETER("요청 파라미터가 잘못되었습니다."),
    UNAUTHORIZED("인증에 실패하였습니다."),
    USER_NOT_FOUND("존재하지 않는 회원입니다."),

    SERVER_ERROR("서버 에러입니다.");

    private String message;

}
