package com.jojoldu.bns.admin.service.dto.bitly;

import com.jojoldu.bns.core.domain.link.OriginLink;
import com.jojoldu.bns.core.domain.link.SnsType;
import lombok.Builder;
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

    @Builder
    public BitlyLinkRequestDto(String title, String content, String link, List<SnsType> snsTypes) {
        this.title = title;
        this.content = content;
        this.link = link;
        this.snsTypes = snsTypes;
    }

    public List<BitlyLinkExchangeDto> toExchangeDtoList(String groupGuid) {
        return snsTypes.stream()
                .map(s -> BitlyLinkExchangeDto.builder()
                        .groupGuid(groupGuid)
                        .longUrl(link)
                        .title(String.format("[%s] %s", s.getTitle(), title))
                        .tags(Collections.singletonList(s.name()))
                        .snsType(s)
                        .build())
                .collect(Collectors.toList());
    }

    public OriginLink toOriginLink() {
        return OriginLink.builder()
                .title(title)
                .content(content)
                .link(link)
                .build();
    }

}
