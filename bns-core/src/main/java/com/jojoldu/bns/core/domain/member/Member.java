package com.jojoldu.bns.core.domain.member;

import com.jojoldu.bns.core.domain.BaseTimeEntity;
import com.jojoldu.bns.core.domain.link.OriginLink;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 27.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@Getter
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uni_member_username", columnNames = "username"),
                @UniqueConstraint(name = "uni_member_email", columnNames = "email")
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    private String picture;

    private String accessToken;

    @Column(name = "is_active")
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<OriginLink> originLinks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<FacebookPage> facebookPages = new ArrayList<>();

    @Builder
    public Member(@Nonnull String name, @Nonnull String username, @Nonnull String email, String picture, String accessToken, boolean isActive, @Nonnull Role role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.picture = picture;
        this.accessToken = accessToken;
        this.isActive = isActive;
        this.role = role;
    }

    public void refreshToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void addOriginLink(OriginLink originLink) {
        this.originLinks.add(originLink);
        originLink.setMember(this);
    }

    public void addFacebookPage(List<FacebookPage> facebookPages) {
        for (FacebookPage facebookPage : facebookPages) {
            this.addFacebookPage(facebookPage);
        }
    }

    public void addFacebookPage(FacebookPage facebookPage) {
        this.facebookPages.add(facebookPage);
        facebookPage.setMember(this);
    }
}
