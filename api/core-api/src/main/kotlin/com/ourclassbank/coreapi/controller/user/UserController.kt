package com.ourclassbank.coreapi.controller.user

import com.ourclassbank.coreapi.controller.common.UserResponse
import com.ourclassbank.coredomain.service.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "회원", description = "auth: STUDENT")
@RestController
class UserController(
    private val userService: UserService
) {
    @Operation(summary = "전체 조회 - 같은 반")
    @GetMapping("/api/v1/user/same-class")
    fun findSameClass(): List<UserResponse> {
        return userService.findAllSameClass().map { UserResponse(it) }
    }
}
