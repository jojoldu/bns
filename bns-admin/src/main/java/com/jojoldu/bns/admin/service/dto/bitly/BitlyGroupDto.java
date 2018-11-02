package com.jojoldu.bns.admin.service.dto.bitly;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class BitlyGroupDto {
    private List<Group> groups;

    public String getGroupGuid() {
        if (groups.isEmpty()) {
            return "";
        }
        return groups.get(0).getGuid();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Group {
        private References references;
        private String role;
        private boolean isActive;
        private String name;
        private String organizationGuid;
        private String guid;
        private List<String> bsds;
        private String modified;
        private String created;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class References {
        private String organization;
    }


}
