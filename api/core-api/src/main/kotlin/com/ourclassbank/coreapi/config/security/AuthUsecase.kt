package com.ourclassbank.coreapi.config.security

import com.ourclassbank.coreapi.config.security.jwt.JwtTokenProvider
import com.ourclassbank.coredomain.service.UserService
import com.ourclassbank.modeldomain.user.RoleType
import com.ourclassbank.modeldomain.user.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthUsecase(
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,

    private val userService: UserService
) {
    fun signup(loginId: String, password: String, name: String, roles: List<RoleType>) {
        userService.save(User(loginId, passwordEncoder.encode(password), name, roles))
    }

    fun signin(loginId: String, password: String): String {
        val user = userService.findByLoginId(loginId)
        if (!passwordEncoder.matches(password, user.password)) {
            throw RuntimeException("비밀번호가 일치하지 않습니다.")
        }

        return jwtTokenProvider.createToken(user)
    }
}
