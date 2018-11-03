package com.jojoldu.bns.admin.service.dto.bitly;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jojoldu.bns.core.domain.link.SnsLink;
import com.jojoldu.bns.core.domain.link.SnsType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2018. 11. 2.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitlyCreateResponseDto {

    @JsonProperty("created_at")
    private String createdAt;
    private String id;
    private String link;
    @JsonProperty("custom_bitlinks")
    private List<String> customBitlinks;
    @JsonProperty("long_url")
    private String longUrl;
    private String title;
    private boolean archived;
    private List<String> tags;
    private List<String> deeplinks;
    private References references;

    public BitlyCreateResponseDto(String link) {
        this.link = link;
    }

    public SnsLink toSnsLink(SnsType snsType) {
        return SnsLink.builder()
                .snsType(snsType)
                .link(link)
                .build();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class References {
        private String group;
    }
}
