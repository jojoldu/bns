package com.jojoldu.bns.admin.web;

import lombok.Getter;

/**
 * Created by jojoldu@gmail.com on 2018. 11. 6.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
public class BnsAdminException extends RuntimeException {
    private ResponseCode status;
    private String message;

    public BnsAdminException(ResponseCode status, Exception e) {
        super(e);
        this.status = status;
        this.message = status.getMessage();
    }

    public BnsAdminException(ResponseCode status, String message, Exception e) {
        super(e);
        this.status = status;
        this.message = message;
    }

    public BnsAdminException(ResponseCode status) {
        this.status = status;
        this.message = status.getMessage();
    }

    public BnsAdminException(ResponseCode status, String message) {
        this.status = status;
        this.message = message;
    }
}
