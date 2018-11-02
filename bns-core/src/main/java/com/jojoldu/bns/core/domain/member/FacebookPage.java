package com.jojoldu.bns.core.domain.member;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 28.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

import com.jojoldu.bns.core.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class FacebookPage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accessToken;
    private String pageName;
    private String pageId;
    private String category;

    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_facebook_page_member"))
    private Member member;

    @Builder
    public FacebookPage(@Nonnull String accessToken, @Nonnull String pageName, @Nonnull String pageId, @Nonnull String category) {
        this.accessToken = accessToken;
        this.pageName = pageName;
        this.pageId = pageId;
        this.category = category;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}