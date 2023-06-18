package com.ourclassbank.coreapi.controller.auth

import com.ourclassbank.coreapi.config.security.AuthUsecase
import com.ourclassbank.modeldomain.user.RoleType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Tag(name = "인증/인가")
@RestController
class AuthController(
    private val authUsecase: AuthUsecase,
) {
    @Operation(summary = "회원 가입")
    @PostMapping("/api/v1/auth/signup")
    fun signup(@RequestBody request: UserSignupRequest) {
        return authUsecase.signup(request.loginId, request.password, request.name, request.roles)
    }

    @Operation(summary = "회원 로그인")
    @PostMapping("/api/v1/auth/signin")
    fun signin(@RequestBody request: UserSigninRequest): UserSigninResponse {
        return UserSigninResponse(authUsecase.signin(request.loginId, request.password))
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

data class UserSigninResponse(
    val accessToken: String,
)
