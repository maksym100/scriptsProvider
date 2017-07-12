package com.example.kotlin.filter

import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author madamek
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoCacheFilterTest {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun `Responses from server have headers preventing caching`() {
        val result = testRestTemplate.getForEntity("/script/max", String::class.java)
        assertNotNull(result)
        log.debug("Cache control headers are {}", result.headers.cacheControl)
        assertThat(result.headers.cacheControl).contains("no-cache")
        assertThat(result.headers.cacheControl).contains("no-store")
        assertThat(result.headers.cacheControl).contains("must-revalidate")
        assertThat(result.headers.pragma).contains("no-cache")
        assertThat(result.headers.expires).isEqualTo(-1)
    }
}