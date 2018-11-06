package com.jojoldu.bns.admin.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Nonnull;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 28.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@ToString
@NoArgsConstructor
@Getter
public class ResponseDto<T> {
    public static ResponseDto<String> DEFAULT_OK = new ResponseDto<>(ResponseCode.OK);
    public static ResponseDto<String> DEFAULT_UNAUTHORIZED = new ResponseDto<>(ResponseCode.UNAUTHORIZED);

    private ResponseCode code;
    private String message;
    private T data;

    private ResponseDto(@Nonnull ResponseCode status) {
        this.bindStatus(status);
    }

    private ResponseDto(@Nonnull ResponseCode status, @Nonnull T data) {
        this.bindStatus(status);
        this.data = data;
    }

    private ResponseDto(@Nonnull ResponseCode code, @Nonnull String message, @Nonnull T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseDto<T> createOK(@Nonnull T data) {
        return new ResponseDto<>(ResponseCode.OK, data);
    }

    public static ResponseDto<String> createException(@Nonnull BnsAdminException e) {
        return new ResponseDto<>(e.getStatus(), e.getMessage(), "");
    }

    public static ResponseDto<String> createException(@Nonnull ResponseCode code) {
        return new ResponseDto<>(code, code.getMessage(), "");
    }

    public static ResponseDto<String> createException(@Nonnull ResponseCode code, @Nonnull String message) {
        return new ResponseDto<>(code, message, "");
    }

    public static <T> ResponseDto<T> createException(@Nonnull ResponseCode code, @Nonnull T data) {
        return new ResponseDto<>(code, data);
    }

    private void bindStatus(ResponseCode status) {
        this.code = status;
        this.message = status.getMessage();
    }
}
