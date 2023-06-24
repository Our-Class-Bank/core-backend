package com.ourclassbank.coreapi.support.exception

import com.fasterxml.jackson.annotation.JsonInclude
import com.ourclassbank.coredomain.support.exception.DomainException
import jakarta.validation.UnexpectedTypeException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import java.time.LocalDateTime
import java.util.*

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ErrorResponse(
    val code: String,
    val message: String,
) {
    val timestamp: LocalDateTime = LocalDateTime.now()
    val traceId: String = UUID.randomUUID().toString()

    constructor(e: DomainException) : this(e.exceptionType.name, e.exceptionType.message)
    constructor(e: MethodArgumentNotValidException) : this(HttpStatus.BAD_REQUEST.name, e.message)
    constructor(e: UnexpectedTypeException) : this(HttpStatus.BAD_REQUEST.name, e.localizedMessage)
    constructor(e: Exception) : this(HttpStatus.INTERNAL_SERVER_ERROR.name, e.localizedMessage)
}
