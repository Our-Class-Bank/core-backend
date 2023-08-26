package com.ourclassbank.coreapi.controller.test

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "test api - 인증/인가")
@RestController
class TestAuthController {
    @Operation(summary = "NULL", description = "권한이 필요하지 않습니다.")
    @GetMapping("/test/api/v1/test/auth/null")
    fun roleNull(): String {
        return "no auth success"
    }

    @Operation(summary = "ROLE_STUDENT", description = "student 권한을 검증 합니다.")
    @GetMapping("/test/api/v1/auth/student")
    fun roleUser(): String {
        return "student success"
    }

    @Operation(summary = "ROLE_BANKER", description = "banker 권한을 검증 합니다.")
    @GetMapping("/test/api/v1/test/auth/banker")
    fun roleBanker(): String {
        return "banker success"
    }

    @Operation(summary = "ROLE_TEACHER", description = "teacher 권한을 검증 합니다.")
    @GetMapping("/test/api/v1/test/auth/teacher")
    fun roleTeacher(): String {
        return "teacher success"
    }
}
