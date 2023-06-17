package com.ourclassbank.coreapi.controller.auth

import com.ourclassbank.coredomain.model.RoleType
import com.ourclassbank.coredomain.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Tag(name = "회원")
@RestController
class UserController(
    private val userService: UserService
) {
    @Operation(summary = "가입")
    @PostMapping("/api/v1/user/signup")
    fun signup(@RequestBody request: UserSignupRequest) {
        return userService.signup(request.loginId, request.password, request.name, request.roles)
    }

    @Operation(summary = "로그인")
    @PostMapping("/api/v1/user/signin")
    fun signin(@RequestBody request: UserSigninRequest) {
        require(userService.findByLoginId(request.loginId).password == request.password) { "비밀번호가 일치하지 않습니다." }
    }
}

data class UserSignupRequest(
    val loginId: String,
    val password: String,
    val name: String,
    val roles: List<RoleType>,
)

data class UserSigninRequest(
    val loginId: String,
    val password: String,
)
