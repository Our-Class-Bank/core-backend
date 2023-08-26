package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.support.jwt.JwtTokenProvider
import com.ourclassbank.coredomain.usecase.AdminUsecase
import com.ourclassbank.coredomain.usecase.UserCommandUsecase
import com.ourclassbank.coredomain.usecase.UserQueryUsecase
import com.ourclassbank.modeldomain.user.RoleType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,

    private val userCommandUsecase: UserCommandUsecase,
    private val userQueryUsecase: UserQueryUsecase
) : AdminUsecase {
    override fun signup(username: String, password: String, name: String, roles: List<RoleType>) {
        TODO()
//        userCommandUsecase.create(
//            User(
//                username = username,
//                password = passwordEncoder.encode(password),
//                name = name,
//                pocketmoneyAccountNo = "1234-1234-1234-1234",
//                roles = roles,
//                userClass = UserClass(
//                    schoolName = "우리초등학교",
//                    grade = 3,
//                    classNumber = 1,
//                    attendanceNumber = 3
//                )
//            )
//        )
    }
}
