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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uni_facebook_page_page_id", columnNames = {"pageId"})
        }
)
public class FacebookPage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accessToken;
    private String pageName;

    @Column(nullable = false)
    private String pageId;
    private String category;

    @Column(name = "is_default")
    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_facebook_page_member"))
    private Member member;

    @Builder
    public FacebookPage(@Nonnull String accessToken, @Nonnull String pageName, @Nonnull String pageId, @Nonnull String category) {
        this.accessToken = accessToken;
        this.pageName = pageName;
        this.pageId = pageId;
        this.category = category;
        this.isDefault = false;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void onDefault() {
        this.isDefault = true;
    }
}
