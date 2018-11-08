package com.jojoldu.bns.admin.web.api;

import com.jojoldu.bns.admin.aspect.LoginUser;
import com.jojoldu.bns.admin.config.oauth.dto.SessionUser;
import com.jojoldu.bns.admin.service.BitlyService;
import com.jojoldu.bns.admin.service.dto.bitly.BitlyLinkRequestDto;
import com.jojoldu.bns.admin.service.dto.bitly.SendSnsRequestDto;
import com.jojoldu.bns.admin.web.ResponseCode;
import com.jojoldu.bns.admin.web.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 28.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class AdminApiController {
    private final BitlyService bitlyService;

    @PostMapping("/admin/v1/bitly")
    public ResponseDto<String> createBitly(@RequestBody BitlyLinkRequestDto requestDto, @LoginUser SessionUser sessionUser) {
        try {
            bitlyService.createBitlyLink(requestDto, sessionUser.getGuid(), sessionUser.getAccessToken(), sessionUser.getEmail());
        } catch (Exception e) {
            return ResponseDto.createException(ResponseCode.SERVER_ERROR, e.getMessage());
        }
        return ResponseDto.DEFAULT_OK;
    }

    @PostMapping("/admin/v1/send/sns")
    public ResponseDto<String> sendSnsAll(@RequestBody SendSnsRequestDto requestDto, @LoginUser SessionUser sessionUser) {
        try {
            bitlyService.sendAll(requestDto, sessionUser.getEmail());
        } catch (Exception e) {
            return ResponseDto.createException(ResponseCode.SERVER_ERROR, e.getMessage());
        }
        return ResponseDto.DEFAULT_OK;
    }


}
