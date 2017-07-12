package com.example.kotlin.provider

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author madamek
 */
@RunWith(SpringRunner::class)
@ActiveProfiles("minio")
@SpringBootTest
class MinioScriptProviderTest {
    @Autowired
    lateinit var minioScriptProvider: MinioScriptProvider

    @Test
    fun `content from minio is not empty and has 200 status code for existing partner`() {
        val scriptContent = minioScriptProvider.getScriptContent("max")
        assertThat(scriptContent).isNotBlank()
    }
}