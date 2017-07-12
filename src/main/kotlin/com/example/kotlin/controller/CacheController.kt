package com.example.kotlin.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
 * @author madamek
 */
@RestController
class CacheController @Autowired constructor(val cacheManager: CaffeineCacheManager) {

    @GetMapping("/clean/{cacheName}")
    fun cacheClean(@PathVariable("cacheName") cacheName: String) {
        cacheManager.getCache(cacheName)?.clear()
    }
}