package com.jojoldu.bns.admin.web.api;

import com.jojoldu.bns.admin.aspect.LoginUser;
import com.jojoldu.bns.admin.config.oauth.dto.SessionUser;
import com.jojoldu.bns.admin.web.api.dto.BitlyLinkRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 28.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class BitlyLinkAdminApiController {

    public List<String> createBitlyLink(BitlyLinkRequestDto requestDto, @LoginUser SessionUser sessionUser) {
        List<String> resultLinks = new ArrayList<>();

        return resultLinks;
    }
}
