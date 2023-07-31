package com.ourclassbank.coreapi.controller.creditevaluation

import com.ourclassbank.coreapi.controller.common.CreditEvaluationHistoryResponse
import com.ourclassbank.coreapi.controller.creditevaluation.request.CreditEvaluateRequest
import com.ourclassbank.coreapi.controller.creditevaluation.response.CreditEvaluateResponse
import com.ourclassbank.coredomain.service.creditevaluation.CreditEvaluationReadService
import com.ourclassbank.coredomain.service.creditevaluation.CreditEvaluationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@Tag(name = "신용평가", description = "auth: CREDIT_SCORE_MANAGER")
@RestController
class CreditEvaluationController(
    private val creditEvaluationService: CreditEvaluationService,
    private val creditEvaluationReadService: CreditEvaluationReadService
) {
    @Operation(summary = "개인 회원 평가")
    @PostMapping("/api/v1/credit-evaluation/{username}")
    fun evaluate(@PathVariable username: String, @RequestBody request: CreditEvaluateRequest): CreditEvaluateResponse {
        return creditEvaluationService.evaluate(request.toVo(username)).run {
            CreditEvaluateResponse.from(this)
        }
    }

    @Operation(summary = "이력 조회")
    @GetMapping("/api/v1/credit-evaluation/history")
    fun findAll(
        @RequestParam username: String,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) fromAt: LocalDateTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) toAt: LocalDateTime
    ): List<CreditEvaluationHistoryResponse> {
        return creditEvaluationReadService.findAllHistoryByUser(username, fromAt, toAt).run {
            this.map { CreditEvaluationHistoryResponse.from(it) }
        }
    }
}
