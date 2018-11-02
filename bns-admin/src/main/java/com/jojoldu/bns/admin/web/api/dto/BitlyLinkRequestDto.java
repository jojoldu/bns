package com.jojoldu.bns.admin.web.api.dto;

import com.jojoldu.bns.admin.service.dto.bitly.BitlyLinkExchangeDto;
import com.jojoldu.bns.core.domain.link.SnsType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 28.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@Setter
@NoArgsConstructor
public class BitlyLinkRequestDto {

    private String title;
    private String content;
    private String link;
    private List<SnsType> snsTypes = new ArrayList<>();

    public List<BitlyLinkExchangeDto> toExchangeDtoList(String groupGuid) {
        return snsTypes.stream()
                .map(s -> BitlyLinkExchangeDto.builder()
                        .groupGuid(groupGuid)
                        .longUrl(link)
                        .title(title)
                        .tags(Collections.singletonList(s.name()))
                        .build())
                .collect(Collectors.toList());
    }
}
