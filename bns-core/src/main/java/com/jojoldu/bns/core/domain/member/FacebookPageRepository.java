package com.jojoldu.bns.core.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 28.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

public interface FacebookPageRepository extends JpaRepository<FacebookPage, Long> {
    Optional<FacebookPage> findByMember(Member member);
}
