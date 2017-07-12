package com.example.kotlin.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author madamek
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class ScriptException(message: String) : Exception(message)