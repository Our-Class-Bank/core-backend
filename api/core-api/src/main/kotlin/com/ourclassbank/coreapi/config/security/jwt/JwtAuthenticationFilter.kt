package com.ourclassbank.coreapi.config.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

class JwtAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val token = (request as HttpServletRequest).getHeader(HttpHeaders.AUTHORIZATION)?.let {
            jwtTokenProvider.resolveToken(it)
        } ?: ""

        if (jwtTokenProvider.validate(token)) {
            SecurityContextHolder.getContext().authentication = jwtTokenProvider.getAuthentication(token)
        }

        chain.doFilter(request, response)
    }
}
