package com.ourclassbank.coredomain.usecase

import com.ourclassbank.coredomain.service.UserService
import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.coredomain.support.exception.DomainExceptionType
import com.ourclassbank.coredomain.support.jwt.JwtTokenProvider
import com.ourclassbank.modeldomain.user.RoleType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthUsecase(
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,

    private val userService: UserService
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
        return userService.findByUsername(username).let {
            if (!passwordEncoder.matches(password, it.password)) {
                throw DomainException(DomainExceptionType.INVALID_USER_PASSWORD)
            }

            jwtTokenProvider.createToken(it)
        }
    }

    fun passwordReset(username: String, name: String) {
        userService.passwordReset(username, name)
    }
}
