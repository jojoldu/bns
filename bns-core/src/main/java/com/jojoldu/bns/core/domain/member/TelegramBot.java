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
                @UniqueConstraint(name = "uni_telegram_bot_link", columnNames = {"link"})
        }
)
public class TelegramBot extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private String link;
    private String name;

    @Column(name = "is_default")
    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_telegram_bot_member"))
    private Member member;

    @Builder
    public TelegramBot(String token, String link, String name, boolean isDefault, Member member) {
        this.token = token;
        this.link = link;
        this.name = name;
        this.isDefault = isDefault;
        this.member = member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void onDefault() {
        this.isDefault = true;
    }
}
