package com.ourclassbank.coreapi.controller.admin

import com.ourclassbank.coreapi.controller.auth.request.UserSignupRequest
import com.ourclassbank.coredomain.usecase.AdminUsecase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "admin api", description = "auth: ADMIN")
@RestController
class AdminController(
    private val adminUsecase: AdminUsecase
) {
    @Operation(summary = "회원가입")
    @PostMapping("/admin/api/v1/user")
    fun signup(@RequestBody request: UserSignupRequest) {
        return adminUsecase.signup(request.username, request.password, request.name, request.roles)
    }
}
