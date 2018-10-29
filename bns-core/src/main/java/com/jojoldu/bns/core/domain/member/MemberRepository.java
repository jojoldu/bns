package com.jojoldu.bns.core.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by jojoldu@gmail.com on 2018. 10. 27.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findByEmail(String email);
}
