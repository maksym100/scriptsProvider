package com.example.kotlin.config

import com.example.kotlin.exception.ScriptException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


/**
 * @author madamek
 */
@ControllerAdvice
class ScriptAdvice : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = *arrayOf(ScriptException::class))
    fun handleConflict(ex: ScriptException, request: WebRequest): ResponseEntity<Any> {
        val bodyOfResponse = ex.message
        return handleExceptionInternal(ex, bodyOfResponse,
                HttpHeaders(), HttpStatus.BAD_REQUEST, request)
    }
}