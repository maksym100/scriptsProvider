package com.example.kotlin.controller

import com.example.kotlin.provider.ScriptProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.util.stream.Collectors

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

    @GetMapping("/scripts")
    fun getCombineScripts(@RequestParam("partners") partners: List<String>): String {
        partners.stream().map(scriptProvider::getScriptContent).collect(Collectors.toList())

        val scripts = partners.map { partner: String -> scriptProvider.getScriptContent(partner) }
        val ctx = Context()
        ctx.setVariable("scriptBodies", scripts)
        return template.process("multiTemplate", ctx)
    }
}