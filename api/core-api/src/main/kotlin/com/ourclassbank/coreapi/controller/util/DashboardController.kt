package com.ourclassbank.coreapi.controller.util

import com.ourclassbank.coredomain.usecase.DashboardQueryUsecase
import com.ourclassbank.modeldomain.user.RoleType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "로그인 대시보드")
@RestController
class DashboardController(
    private val dashboardQueryUsecase: DashboardQueryUsecase
) {
    @Operation(summary = "전체 회원 수 조회")
    @GetMapping("/util/api/v1/dashboard/user-count")
    fun userCount(): Long {
        return dashboardQueryUsecase.userCount()
    }

    @Operation(summary = "전체 권한")
    @GetMapping("/util/api/v1/dashboard/role")
    fun role(): List<RoleType> {
        return dashboardQueryUsecase.userRole()
    }

    @Operation(summary = "전체 입출금 이력 횟수")
    @GetMapping("/util/api/v1/dashboard/account-history-count")
    fun accountHistoryCount(): Long {
        return dashboardQueryUsecase.accountHistoryCount()
    }

    @Operation(summary = "전체 신용평가 횟수")
    @GetMapping("/util/api/v1/dashboard/credit-evaluation-count")
    fun creditEvaluationCount(): Long {
        return dashboardQueryUsecase.creditEvaluationCount()
    }
}
