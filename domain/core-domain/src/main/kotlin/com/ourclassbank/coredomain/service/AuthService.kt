package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.coredomain.support.exception.DomainExceptionType.INSUFFICIENT_USER_PASSWORD_CHANGE
import com.ourclassbank.coredomain.support.exception.DomainExceptionType.INVALID_USER_PASSWORD
import com.ourclassbank.coredomain.support.jwt.JwtTokenProvider
import com.ourclassbank.coredomain.usecase.AuthUsecase
import com.ourclassbank.coredomain.usecase.UserCommandUsecase
import com.ourclassbank.coredomain.usecase.UserQueryUsecase
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,

    private val userCommandUsecase: UserCommandUsecase,
    private val userQueryUsecase: UserQueryUsecase
) : AuthUsecase {
    override fun signin(username: String, password: String): String {
        return userQueryUsecase.findByUsername(username).let {
            if (!passwordEncoder.matches(password, it.password)) {
                throw DomainException(INVALID_USER_PASSWORD)
            }

            jwtTokenProvider.createToken(it)
        }
    }

    override fun passwordReset(username: String, name: String) {
        userCommandUsecase.passwordReset(username, name)
    }

    override fun passwordChange(username: String, name: String, newPassword: String) {
        if (!userCommandUsecase.passwordChangeAble(username, name)) {
            throw DomainException(INSUFFICIENT_USER_PASSWORD_CHANGE)
        }

        userCommandUsecase.passwordChange(username, newPassword)
    }
}
