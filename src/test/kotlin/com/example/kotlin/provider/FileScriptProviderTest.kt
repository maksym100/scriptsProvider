package com.example.kotlin.provider

import org.assertj.core.api.Assertions
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource

/**
 * @author madamek
 */
class FileScriptProviderTest {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    val fileScriptProvider: FileScriptProvider = FileScriptProvider(ClassPathResource("scripts"))

    @Test
    fun `provider respond with not blank content for existing partner`() {
        val scriptContent = fileScriptProvider.getScriptContent("test")
        Assertions.assertThat(scriptContent).isNotBlank()
        log.debug("Content: {}", scriptContent)
    }

}
