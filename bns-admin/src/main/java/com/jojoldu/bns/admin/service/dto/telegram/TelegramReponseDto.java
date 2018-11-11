package com.jojoldu.bns.admin.service.dto.telegram;

/**
 * Created by jojoldu@gmail.com on 2018. 11. 11.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class TelegramReponseDto {
    private long totalCount;

    public TelegramReponseDto(long totalCount) {
        this.totalCount = totalCount;
    }
}
