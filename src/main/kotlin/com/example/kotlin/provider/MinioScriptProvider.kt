package com.example.kotlin.provider

import com.example.kotlin.exception.ScriptException
import io.minio.MinioClient
import io.minio.errors.ErrorResponseException
import org.apache.commons.io.IOUtils
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.nio.charset.Charset

/**
 * @author madamek
 */
@Component
@Profile("minio")
@Primary
class MinioScriptProvider(val minioClient: MinioClient) : ScriptProvider {
    @Cacheable("scripts")
    override fun getScriptContent(partner: String): String {
        try {
            val scripts = minioClient.getObject("scripts", "$partner.js")
            return IOUtils.toString(scripts, Charset.forName("UTF-8"))
        } catch (e: ErrorResponseException) {
            throw ScriptException("Can't get script: $e.message")
        }
    }
}
