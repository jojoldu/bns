package com.jojoldu.bns.admin.service

import com.jojoldu.bns.admin.service.dto.bitly.BitlyCreateResponseDto
import com.jojoldu.bns.admin.service.dto.bitly.BitlyLinkRequestDto
import com.jojoldu.bns.core.domain.link.OriginLinkRepository
import com.jojoldu.bns.core.domain.link.SnsLinkRepository
import com.jojoldu.bns.core.domain.link.SnsType
import com.jojoldu.bns.core.domain.member.Member
import com.jojoldu.bns.core.domain.member.MemberRepository
import com.jojoldu.bns.core.domain.member.Role
import org.springframework.beans.factory.annotation.Autowired

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.BDDMockito.given

/**
 * Created by jojoldu@gmail.com on 2018. 11. 3.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

class BitlyServiceTest extends SpringMockAdminTest {

    @Autowired
    BitlyService bitlyService

    @Autowired
    MemberRepository memberRepository

    @Autowired
    OriginLinkRepository originLinkRepository

    @Autowired
    SnsLinkRepository snsLinkRepository

    void cleanup() {
        snsLinkRepository.deleteAllInBatch()
        originLinkRepository.deleteAllInBatch()
        memberRepository.deleteAllInBatch()
    }

    def "SnsType대로 Bitly링크로 전환되어 member, originLink, snsLink 엔티티에 저장된다"() {
        given:
        String email = "test"
        String guid = "guid"
        String accessToken = "accessToken"

        memberRepository.save(Member.builder()
                .name("1")
                .username("1")
                .email(email)
                .isActive(true)
                .role(Role.ADMIN)
                .build())

        List<SnsType> snsTypes = Arrays.asList(SnsType.FACEBOOK)
        BitlyLinkRequestDto requestDto = BitlyLinkRequestDto.builder()
                .title("title")
                .content("content")
                .link("link")
                .snsTypes(snsTypes)
                .build()

        String link1 = "a"

        given(bitlyRestTemplate.create(anyString(), any()))
                .willReturn(new BitlyCreateResponseDto(link1))

        when:
        List<String> snsLinks = bitlyService.createBitlyLink(requestDto, guid, accessToken, email)

        then:
        snsTypes.size() == snsLinks.size()
        originLinkRepository.findAll().size() == 1
        snsLinkRepository.findAll().size() == 1
    }
}
