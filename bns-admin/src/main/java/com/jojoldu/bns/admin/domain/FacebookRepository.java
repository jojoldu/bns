package com.jojoldu.bns.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 28.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

public interface FacebookRepository extends JpaRepository<Facebook, Long> {
    Optional<Facebook> findByMember(Member member);
}
