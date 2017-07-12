package com.example.kotlin.provider

import com.example.kotlin.exception.ScriptException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths

/**
 * @author madamek
 */
@Component
class FileScriptProvider(@Value(value = "#{'\${scripts.directory:classpath:scripts}'}") val scriptsDir: Resource)
    : ScriptProvider {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Cacheable("scripts")
    override fun getScriptContent(partner: String): String {
        logger.debug("Getting script from file for {} partner", partner)
        val uri = Paths.get(scriptsDir.uri).resolve(partner + ".js")
        if (!uri.toFile().exists()) {
            throw ScriptException("Script for partner " + partner + " don't exists")
        }
        return String(Files.readAllBytes(uri))
    }
}