package com.jojoldu.bns.admin.service

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Specification
import spock.mock.DetachedMockFactory

/**
 * Created by jojoldu@gmail.com on 2018. 11. 2.
 * Blog : http://jojoldu.tistory.com
 * Github : https://github.com/jojoldu
 */

@SpringBootTest
abstract class SpringMockTest extends Specification {

    @MockBean
    FacebookRestTemplate facebookRestTemplate

    @TestConfiguration
    static class SpockMockConfiguration {

        def factory = new DetachedMockFactory()
    }
}
