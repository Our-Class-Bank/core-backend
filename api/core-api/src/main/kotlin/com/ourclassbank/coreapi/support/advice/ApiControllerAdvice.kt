package com.ourclassbank.coreapi.support.advice

import com.ourclassbank.coreapi.support.exception.ErrorResponse
import com.ourclassbank.coredomain.support.exception.DomainException
import jakarta.validation.UnexpectedTypeException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DomainException::class)
    fun handleDomainException(e: DomainException): ErrorResponse {
        return ErrorResponse(e).also { log.info(e, it.traceId) }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ErrorResponse {
        return ErrorResponse(e).also { log.info(e, it.traceId) }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnexpectedTypeException::class)
    fun handleUnexpectedTypeException(e: UnexpectedTypeException): ErrorResponse {
        return ErrorResponse(e).also { log.info(e, it.traceId) }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ErrorResponse {
        return ErrorResponse(e).also { log.error(e, it.traceId) }
    }

    private fun Logger.info(e: Exception, traceId: String) {
        info("${e::class.simpleName}: traceId=${traceId}\n" + e.stackTraceToString())
    }

    private fun Logger.error(e: Exception, traceId: String) {
        error("${e::class.simpleName}: traceId=${traceId}\n" + e.stackTraceToString())
    }
}
