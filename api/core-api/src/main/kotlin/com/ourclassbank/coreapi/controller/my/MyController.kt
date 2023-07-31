package com.ourclassbank.coreapi.controller.my

import com.ourclassbank.coreapi.controller.common.AccountResponse
import com.ourclassbank.coreapi.controller.common.CreditEvaluationHistoryResponse
import com.ourclassbank.coredomain.service.UserService
import com.ourclassbank.coredomain.service.creditevaluation.CreditEvaluationReadService
import com.ourclassbank.coredomain.support.security.UserContext
import com.ourclassbank.modeldomain.common.AccountType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@Tag(name = "내 정보", description = "auth: STUDENT")
@RestController
class MyController(
    private val creditEvaluationReadService: CreditEvaluationReadService,
    private val userService: UserService
) {
    @Operation(summary = "내 신용평가 이력")
    @GetMapping("/api/v1/my/credit-evaluation/history")
    fun findAllMyCreditEvaluation(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) fromAt: LocalDateTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) toAt: LocalDateTime
    ): List<CreditEvaluationHistoryResponse> {
        val userContext = SecurityContextHolder.getContext().authentication.principal as UserContext
        return creditEvaluationReadService.findAllHistoryByUser(userContext.uUsername, fromAt, toAt).run {
            this.map { CreditEvaluationHistoryResponse.from(it) }
        }
    }

    @Operation(summary = "내 계좌 목록", description = "현재 존재하는 계좌는 용돈계좌뿐 입니다.")
    @GetMapping("/api/v1/my/account")
    fun findAllMyAccount(): List<AccountResponse> {
        val userContext = SecurityContextHolder.getContext().authentication.principal as UserContext

        val pocketmoneyAccount = userService.findByUsername(userContext.uUsername).run {
            AccountResponse(AccountType.POCKETMONEY, this.pocketmoneyAccountNo)
        }

        return listOf(pocketmoneyAccount)
    }
}
