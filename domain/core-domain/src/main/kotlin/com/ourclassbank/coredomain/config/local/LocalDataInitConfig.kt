package com.ourclassbank.coredomain.config.local

import com.ourclassbank.coredomain.service.UserService
import com.ourclassbank.modeldomain.user.RoleType
import com.ourclassbank.modeldomain.user.User
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("local")
@Configuration
class LocalDataInitConfig(
    private val userService: UserService
) {
    @PostConstruct
    fun localDataInit() {
        회원_전체.forEach {
            userService.create(it)
        }
    }
}

private val 회원_전체 = listOf(
    User(
        loginId = "user001",
        password = "1234",
        name = "홍길동",
        roles = listOf(RoleType.ROLE_USER),
    ),
    User(
        loginId = "user002",
        password = "1234",
        name = "김철수",
        roles = listOf(RoleType.ROLE_USER),
    ),
    User(
        loginId = "banker001",
        password = "1234",
        name = "김은행",
        roles = listOf(RoleType.ROLE_BANKER),
    ),
    User(
        loginId = "teacher001",
        password = "1234",
        name = "김선생",
        roles = listOf(RoleType.ROLE_TEACHER),
    ),
)
