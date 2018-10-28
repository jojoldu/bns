package com.jojoldu.bns.admin.web;

import com.jojoldu.bns.core.domain.link.SnsType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 28.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@Setter
@NoArgsConstructor
public class SnsLinkRequestDto {

    private String title;
    private String content;
    private String link;
    private List<SnsType> snsTypes = new ArrayList<>();

}
