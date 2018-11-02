package com.jojoldu.bns.admin.web;

import com.jojoldu.bns.admin.aspect.LoginUser;
import com.jojoldu.bns.admin.config.oauth.dto.SessionUser;
import com.jojoldu.bns.admin.service.FacebookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 31.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@RequiredArgsConstructor
@Controller
public class SnsRedirectController {
    private final FacebookService facebookService;

    @GetMapping("/accessToken/facebook")
    public String signInFacebook(String code, @LoginUser SessionUser sessionUser) throws Exception {
        facebookService.saveToken(code, sessionUser.getEmail());
        return "redirect:content.html";
    }

}
