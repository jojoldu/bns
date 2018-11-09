package com.jojoldu.bns.admin.service.dto.telegram;

/**
 * Created by jojoldu@gmail.com on 2018. 11. 9.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class TelegramRequestDto {

    private Message message;

    public TelegramRequestDto(String text) {
        this.message = new Message(text);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message {
        private String text;
    }
}
