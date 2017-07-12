package com.example.kotlin.filter

import org.springframework.stereotype.Component
import javax.servlet.*
import javax.servlet.http.HttpServletResponse


/**
 * @author madamek
 */
@Component
class NoCacheFilter : Filter {
    override fun destroy() {}

    override fun init(filterConfig: FilterConfig?) {}

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpResp = response as HttpServletResponse
        httpResp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate")
        httpResp.setHeader("Pragma", "no-cache")
        httpResp.setHeader("Expires", "0")

        chain?.doFilter(request, response)
    }
}