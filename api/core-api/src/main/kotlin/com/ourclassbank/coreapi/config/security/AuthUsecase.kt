package com.ourclassbank.coreapi.config.security

import com.ourclassbank.coredomain.service.UserService
import com.ourclassbank.coredomain.support.jwt.JwtTokenProvider
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
        userService.create(User(loginId, password, name, roles))
    }

    fun signin(loginId: String, password: String): String {
        return userService.findByLoginId(loginId).let {
            if (!passwordEncoder.matches(password, it.password)) {
                throw RuntimeException("비밀번호가 일치하지 않습니다.")
            }

            jwtTokenProvider.createToken(it)
        }
    }
}
