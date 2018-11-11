package com.jojoldu.bns.admin.service;

import com.jojoldu.bns.admin.service.dto.telegram.TelegramRequestDto;
import com.jojoldu.bns.core.domain.link.SnsLink;
import com.jojoldu.bns.core.domain.member.Telegram;
import com.jojoldu.bns.core.domain.member.TelegramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2018. 11. 11.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class TelegramService {

    private final TelegramRestTemplate telegramRestTemplate;
    private final TelegramRepository telegramRepository;

    @Transactional
    public void sendAll(SnsLink telegramLink, List<Long> telegramIds) {
        for (Long id : telegramIds) {
            Telegram telegram = findTelegram(id);
            telegramRestTemplate.send(telegram.getLink(), telegram.getApiKey(), new TelegramRequestDto(telegramLink.getMessage()));
        }
        telegramLink.send();
    }

    Telegram findTelegram(Long id) {
        return telegramRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Telegram id입니다. id=" + id));
    }

}
