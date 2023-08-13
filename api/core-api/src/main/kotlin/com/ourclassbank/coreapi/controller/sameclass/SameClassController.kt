package com.ourclassbank.coreapi.controller.sameclass

import com.ourclassbank.coreapi.controller.common.UserResponse
import com.ourclassbank.coredomain.service.user.UserReadService
import com.ourclassbank.coredomain.support.security.UserContext
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "같은 반", description = "auth: STUDENT")
@RestController
class SameClassController(
    private val userReadService: UserReadService
) {
    @Operation(summary = "회원 전체 조회")
    @GetMapping("/api/v1/same-class/user")
    fun findAllUser(): List<UserResponse> {
        val userContext = getUserContext()
        return userReadService.findAllSameClass(userContext.uUsername)
            .map { UserResponse(it) }
    }

    private fun getUserContext(): UserContext {
        return SecurityContextHolder.getContext().authentication.principal as UserContext
    }
}
