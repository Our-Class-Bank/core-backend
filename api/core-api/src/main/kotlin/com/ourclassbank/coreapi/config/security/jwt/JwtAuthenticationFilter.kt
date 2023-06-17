package com.ourclassbank.coreapi.config.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

class JwtAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        jwtTokenProvider.resolveToken(request as HttpServletRequest)?.let {
            if (jwtTokenProvider.validate(it)) {
                SecurityContextHolder.getContext().authentication = jwtTokenProvider.getAuthentication(it)
            }
        }

        chain.doFilter(request, response)
    }
}
