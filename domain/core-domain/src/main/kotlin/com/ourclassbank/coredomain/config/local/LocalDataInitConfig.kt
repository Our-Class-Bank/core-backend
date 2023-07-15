package com.ourclassbank.coredomain.config.local

import com.ourclassbank.coredomain.service.UserService
import com.ourclassbank.coredomain.support.generator.PocketMoneyAccountNoGenerator
import com.ourclassbank.modeldomain.user.RoleType
import com.ourclassbank.modeldomain.user.User
import com.ourclassbank.modeldomain.user.UserClass
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
        pocketmoneyAccountNo = PocketMoneyAccountNoGenerator.take(),
        roles = listOf(RoleType.ROLE_STUDENT),
        userClass = UserClass(
            schoolName = "우리초등학교",
            grade = 3,
            classNumber = 1,
            attendanceNumber = 1
        )
    ),
    User(
        loginId = "user002",
        password = "1234",
        name = "김철수",
        pocketmoneyAccountNo = PocketMoneyAccountNoGenerator.take(),
        roles = listOf(RoleType.ROLE_STUDENT),
        userClass = UserClass(
            schoolName = "우리초등학교",
            grade = 3,
            classNumber = 1,
            attendanceNumber = 2
        )
    ),
    User(
        loginId = "banker001",
        password = "1234",
        name = "김은행",
        pocketmoneyAccountNo = PocketMoneyAccountNoGenerator.take(),
        roles = listOf(RoleType.ROLE_STUDENT, RoleType.ROLE_BANKER),
        userClass = UserClass(
            schoolName = "우리초등학교",
            grade = 3,
            classNumber = 1,
            attendanceNumber = 3
        )
    ),
    User(
        loginId = "teacher001",
        password = "1234",
        name = "김선생",
        pocketmoneyAccountNo = PocketMoneyAccountNoGenerator.take(),
        roles = listOf(RoleType.ROLE_TEACHER),
        userClass = UserClass(
            schoolName = "우리초등학교",
            grade = 3,
            classNumber = 1,
            attendanceNumber = 0
        )
    ),
)
