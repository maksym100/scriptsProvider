package com.example.kotlin.config

import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/**
 * @author madamek
 */
@Configuration
@Profile("minio")
class MinioConfig(@Value("\${minio.endpoint}") val minioUrl: String,
                  @Value("\${minio.accessKey}") val minioAccessKey: String,
                  @Value("\${minio.secretKey}") val minioSecretKey: String) {

    @Bean
    fun getMinioClient(): MinioClient {
        return MinioClient(minioUrl, minioAccessKey, minioSecretKey)
    }
}