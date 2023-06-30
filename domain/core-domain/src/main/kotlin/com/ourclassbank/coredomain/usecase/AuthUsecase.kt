package com.ourclassbank.coredomain.usecase

import com.ourclassbank.coredomain.service.UserService
import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.coredomain.support.exception.DomainExceptionType
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
        userService.create(
            User(
                loginId = loginId,
                password = passwordEncoder.encode(password),
                name = name,
                roles = roles
            )
        )
    }

    fun signin(loginId: String, password: String): String {
        return userService.findByLoginId(loginId).let {
            if (!passwordEncoder.matches(password, it.password)) {
                throw DomainException(DomainExceptionType.INVALID_USER_PASSWORD)
            }

            jwtTokenProvider.createToken(it)
        }
    }

    fun passwordReset(loginId: String, name: String) {
        userService.passwordReset(loginId, name)
    }
}
