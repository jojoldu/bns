package com.jojoldu.bns.core.domain.link;

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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
                @UniqueConstraint(name = "uni_sns_link_link", columnNames = {"link"})
        }
)
public class SnsLink extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String link;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private SnsType snsType;

    private long clickCount;

    @ManyToOne
    @JoinColumn(name = "origin_link_id", foreignKey = @ForeignKey(name = "fk_sns_link_origin_link"))
    private OriginLink originLink; // 원본 링크

    @Builder
    public SnsLink(String link, SnsType snsType) {
        this.link = link;
        this.snsType = snsType;
        this.clickCount = 0;
    }

    public void updateOriginLink(OriginLink originLink) {
        this.originLink = originLink;
    }
}
