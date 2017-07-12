package com.example.kotlin.controller

import com.example.kotlin.provider.ScriptProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

/**
 * @author
 */
@RestController
class ScriptController @Autowired constructor(val scriptProvider: ScriptProvider, val template: TemplateEngine) {

    @GetMapping("/script/{partner}")
    fun getScript(@PathVariable("partner") partner: String): String {
        val scriptContent = scriptProvider.getScriptContent(partner)
        val ctx = Context()
        ctx.setVariable("scriptBody", scriptContent)
        return template.process("testTemplate", ctx)
    }
}