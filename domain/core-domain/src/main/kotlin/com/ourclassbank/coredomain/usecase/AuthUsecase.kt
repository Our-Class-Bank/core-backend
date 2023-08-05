package com.ourclassbank.coredomain.usecase

import com.ourclassbank.coredomain.service.user.UserReadService
import com.ourclassbank.coredomain.service.user.UserService
import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.coredomain.support.exception.DomainExceptionType.INSUFFICIENT_USER_PASSWORD_CHANGE
import com.ourclassbank.coredomain.support.exception.DomainExceptionType.INVALID_USER_PASSWORD
import com.ourclassbank.coredomain.support.jwt.JwtTokenProvider
import com.ourclassbank.modeldomain.user.RoleType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthUsecase(
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,

    private val userService: UserService,
    private val userReadService: UserReadService
) {
    fun signup(username: String, password: String, name: String, roles: List<RoleType>) {
        TODO()
//        userService.create(
//            User(
//                username = username,
//                password = passwordEncoder.encode(password),
//                name = name,
//                roles = roles,
//                clazz = Clazz(
//                    schoolName = "우리초등학교",
//                    grade = 3,
//                    classNumber = 1,
//                    attendanceNumber = 3
//                )
//            )
//        )
    }

    fun signin(username: String, password: String): String {
        return userReadService.findByUsername(username).let {
            if (!passwordEncoder.matches(password, it.password)) {
                throw DomainException(INVALID_USER_PASSWORD)
            }

            jwtTokenProvider.createToken(it)
        }
    }

    fun passwordReset(username: String, name: String) {
        userService.passwordReset(username, name)
    }

    fun passwordChange(username: String, name: String, newPassword: String) {
        if (!userService.passwordChangeAble(username, name)) {
            throw DomainException(INSUFFICIENT_USER_PASSWORD_CHANGE)
        }

        userService.passwordChange(username, newPassword)
    }
}
