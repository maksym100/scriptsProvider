package com.example.kotlin.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author madamek
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScriptControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun `controller respond with not blank content and code 200 for existing partner`() {
        val result = testRestTemplate.getForEntity("/script/max", String::class.java)
        assertNotNull(result)
        assertThat(result.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(result.body).isNotBlank()
    }

    @Test
    fun `controller respond with error message and code 400 for not existing partner`() {
        val result = testRestTemplate.getForEntity("/script/notexisting", String::class.java)
        assertNotNull(result)
        assertThat(result.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
        assertThat(result.body).isNotBlank()
    }
}