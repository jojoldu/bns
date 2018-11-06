package com.jojoldu.bns.admin.service.dto.bitly;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by jojoldu@gmail.com on 2018. 11. 5.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */


@ToString
@Getter
@Setter
@NoArgsConstructor
public class SendSnsRequestDto {
    private Long originLinkId;
    private Facebook facebook;


    public List<String> getFacebookPageIds() {
        if (facebook == null) {
            return Collections.emptyList();
        }

        return this.facebook.getPageIds();
    }

    @ToString
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Facebook {
        private List<String> pageIds = new ArrayList<>();
    }

}
