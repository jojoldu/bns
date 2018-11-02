package com.jojoldu.bns.admin.service

import com.jojoldu.bns.admin.service.dto.facebook.FacebookPageAccessToken
import com.jojoldu.bns.core.domain.member.*
import org.springframework.beans.factory.annotation.Autowired

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.BDDMockito.given

/**
 * Created by jojoldu@gmail.com on 2018. 11. 2.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

class FacebookServiceTest extends SpringMockTest {

    @Autowired
    MemberRepository memberRepository

    @Autowired
    FacebookPageRepository facebookPageRepository

    @Autowired
    FacebookService facebookService

    void cleanup() {
        facebookPageRepository.deleteAllInBatch()
        memberRepository.deleteAllInBatch()
    }

    def "member가 저장되면 facebookPage도 함께 저장된다"() {
        given:
        String email = "test"
        memberRepository.save(new Member("name", "username", email, null, null, true, Role.ADMIN))
        FacebookPageAccessToken mockPageToken = new FacebookPageAccessToken(
                Arrays.asList(
                        FacebookPageAccessToken.Page.builder().id("id1").name("name1").category("category1").accessToken("accessToken1").build(),
                        FacebookPageAccessToken.Page.builder().id("id2").name("name2").category("category2").accessToken("accessToken2").build()
                )
        )

        given(facebookRestTemplate.requestAccessToken(anyString())).willReturn(null)
        given(facebookRestTemplate.exchangePageToken(any())).willReturn(mockPageToken)

        when:
        facebookService.saveToken("code", email)

        then:
        List<FacebookPage> facebookPages = facebookPageRepository.findAll()
        facebookPages.size() == 2
    }
}
