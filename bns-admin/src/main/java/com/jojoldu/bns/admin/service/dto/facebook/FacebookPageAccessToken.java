package com.jojoldu.bns.admin.service.dto.facebook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jojoldu.bns.core.domain.member.FacebookPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 31.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookPageAccessToken {

    private List<Page> data;

    public List<FacebookPage> toEntities() {
        return data.stream()
                .map(Page::toEntity)
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Page {
        private List<String> tasks;
        private String id;
        private String name;
        @JsonProperty("category_list")
        private List<CategoryList> categoryList;
        private String category;
        @JsonProperty("access_token")
        private String accessToken;

        @Builder
        public Page(List<String> tasks, @Nonnull String id, @Nonnull String name, List<CategoryList> categoryList, @Nonnull String category, @Nonnull String accessToken) {
            this.tasks = tasks;
            this.id = id;
            this.name = name;
            this.categoryList = categoryList;
            this.category = category;
            this.accessToken = accessToken;
        }

        public FacebookPage toEntity() {
            return FacebookPage.builder()
                    .pageId(id)
                    .pageName(name)
                    .category(category)
                    .accessToken(accessToken)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CategoryList {
        private String name;
        private String id;
    }


}
