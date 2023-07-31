package com.ourclassbank.coreapi.controller.my

import com.ourclassbank.coreapi.controller.common.CreditEvaluationHistoryResponse
import com.ourclassbank.coredomain.service.creditevaluation.CreditEvaluationReadService
import com.ourclassbank.coredomain.support.security.UserContext
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@Tag(name = "내 정보")
@RestController
class MyController(
    private val creditEvaluationReadService: CreditEvaluationReadService
) {
    @Operation(summary = "내 신용평가 이력 조회", description = "- auth: STUDENT")
    @GetMapping("/api/v1/my/credit-evaluation/history")
    fun findAllCreditEvaluation(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) fromAt: LocalDateTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) toAt: LocalDateTime
    ): List<CreditEvaluationHistoryResponse> {
        val userContext = SecurityContextHolder.getContext().authentication.principal as UserContext
        return creditEvaluationReadService.findAllHistoryByUser(userContext.uUsername, fromAt, toAt).run {
            this.map { CreditEvaluationHistoryResponse.from(it) }
        }
    }
}
