package com.jojoldu.bns.admin.service.dto.bitly;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2018. 11. 2.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@Getter
@NoArgsConstructor
public class BitlyLinkExchangeDto {

    @JsonProperty("group_guid")
    private String groupGuid;
    private String domain;

    @JsonProperty("long_url")
    private String longUrl;
    private String title;
    private List<String> tags = new ArrayList<>();

    @Builder
    public BitlyLinkExchangeDto(String groupGuid, String longUrl, String title, List<String> tags) {
        this.groupGuid = groupGuid;
        this.domain = "bit.ly";
        this.longUrl = longUrl;
        this.title = title;
        this.tags = tags;
    }
}
