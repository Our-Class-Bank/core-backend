package com.ourclassbank.coreapi.config.security

import com.ourclassbank.coreapi.config.security.jwt.JwtTokenProvider
import com.ourclassbank.coredomain.service.UserService
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userService: UserService,
    private val jwtTokenProvider: JwtTokenProvider
) {
    fun login(loginId: String, password: String): String {
        val user = userService.findByLoginId(loginId)
        if (user.password != password) {
            throw RuntimeException("비밀번호가 일치하지 않습니다.")
        }
        return jwtTokenProvider.createToken(user)
    }
}
