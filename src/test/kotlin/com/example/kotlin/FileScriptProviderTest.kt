package com.example.kotlin

import com.example.kotlin.provider.FileScriptProvider
import org.assertj.core.api.Assertions
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.ClassPathResource
import java.nio.file.Paths

/**
 * @author madamek
 */
class FileScriptProviderTest {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    val fileScriptProvider: FileScriptProvider = FileScriptProvider(ClassPathResource("scripts"))

    @Test
    fun notBlankScriptContent() {
        val scriptContent = fileScriptProvider.getScriptContent("test")
        Assertions.assertThat(scriptContent).isNotBlank()
        log.debug("Content: {}", scriptContent)
    }

    @Test
    fun name() {
        Paths.get("file:./conf").resolve("max.js")
    }
}
