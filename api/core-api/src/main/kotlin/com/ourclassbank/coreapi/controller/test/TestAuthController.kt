package com.ourclassbank.coreapi.controller.test

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@Tag(name = "인증/인가 테스트 API")
@RestController
class TestAuthController {
    @Operation(summary = "NULL", description = "권한이 필요하지 않습니다.")
    @GetMapping("/api/v1/test/auth/null")
    fun roleNull(): String {
        return "no auth success"
    }

    @Operation(summary = "ROLE_USER", description = "user 권한을 검증 합니다.")
    @GetMapping("/api/v1/test/auth/user")
    fun roleUser(): String {
        return "user success"
    }

    @Operation(summary = "ROLE_BANKER", description = "banker 권한을 검증 합니다.")
    @GetMapping("/api/v1/test/auth/banker")
    fun roleBanker(): String {
        return "banker success"
    }
}
