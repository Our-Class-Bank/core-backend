package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.usecase.AdminUsecase
import com.ourclassbank.coredomain.usecase.UserCommandUsecase
import com.ourclassbank.modeldomain.user.RoleType
import com.ourclassbank.modeldomain.user.User
import com.ourclassbank.modeldomain.user.UserClass
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val passwordEncoder: PasswordEncoder,

    private val userCommandUsecase: UserCommandUsecase,
) : AdminUsecase {
    override fun signup(
        username: String,
        password: String,
        name: String,
        pocketmoneyAccountNo: String,
        roles: List<RoleType>,
        userClass: UserClass
    ) {
        userCommandUsecase.create(
            User(
                username = username,
                password = passwordEncoder.encode(password),
                name = name,
                pocketmoneyAccountNo = pocketmoneyAccountNo,
                roles = roles,
                userClass = userClass
            )
        )
    }
}
