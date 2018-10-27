package com.jojoldu.bns.admin.domain;

import com.jojoldu.bns.core.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;
import javax.persistence.*;

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

    @Builder
    public Member(String name, String username, String email, String picture, String accessToken, boolean isActive, Role role) {
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
}
