package com.example.kotlin.provider

import org.springframework.stereotype.Component

/**
 * @author madamek
 */
@Component
interface ScriptProvider {
    fun getScriptContent(partner: String): String
}