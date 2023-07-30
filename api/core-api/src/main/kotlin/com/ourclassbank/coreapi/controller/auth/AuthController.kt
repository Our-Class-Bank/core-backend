package com.ourclassbank.coreapi.controller.auth

import com.ourclassbank.coredomain.usecase.AuthUsecase
import com.ourclassbank.modeldomain.user.RoleType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "인증/인가")
@RestController
class AuthController(
    private val authUsecase: AuthUsecase,
) {
    @Operation(summary = "회원 가입", description = "능동적인 회원 가입은 지원되지 않습니다.", deprecated = true)
    @PostMapping("/api/v1/auth/signup")
    fun signup(@RequestBody request: UserSignupRequest) {
        return authUsecase.signup(request.username, request.password, request.name, request.roles)
    }

    @Operation(summary = "회원 로그인")
    @PostMapping("/api/v1/auth/signin")
    fun signin(@RequestBody request: UserSigninRequest): UserSigninResponse {
        return UserSigninResponse(authUsecase.signin(request.username, request.password))
    }

    @Operation(summary = "회원 비밀번호 초기화")
    @PostMapping("/api/v1/auth/password-reset")
    fun passwordReset(@RequestBody request: UserPasswordResetRequest) {
        authUsecase.passwordReset(request.username, request.name)
    }
}

data class UserSignupRequest(
    val username: String,
    val password: String,
    val name: String,
    val roles: List<RoleType>,
)

data class UserSigninRequest(
    val username: String,
    val password: String,
)

data class UserSigninResponse(
    val accessToken: String,
)

data class UserPasswordResetRequest(
    val username: String,
    val name: String,
)
