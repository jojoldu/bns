package com.jojoldu.bns.core.domain.link;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 28.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

import com.jojoldu.bns.core.domain.BaseTimeEntity;
import com.jojoldu.bns.core.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uni_origin_link_link", columnNames = {"link"})
        }
)
public class OriginLink extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false, length = 80)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "originLink")
    private List<SnsLink> snsLinks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_origin_link_member"))
    private Member member; //

    @Builder
    public OriginLink(String link, String title, String content) {
        this.link = link;
        this.title = title;
        this.content = content;
    }

    public void addSnsLink(SnsLink snsLink) {
        this.snsLinks.add(snsLink);
        snsLink.updateOriginLink(this);
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
