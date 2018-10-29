package com.jojoldu.bns.core.domain.member;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 28.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

import com.jojoldu.bns.core.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Facebook extends BaseTimeEntity {

    @Id
    private Long id;

    private String accessToken;
    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Member member;

    public Facebook(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
