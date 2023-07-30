package com.ourclassbank.coreapi.controller.creditscore

import com.ourclassbank.coreapi.controller.common.CreditScoreHistoryResponse
import com.ourclassbank.coreapi.controller.creditscore.request.CreditScoreUpdateRequest
import com.ourclassbank.coreapi.controller.creditscore.response.CreditScoreUpdateResponse
import com.ourclassbank.coredomain.service.creditscore.CreditScoreReadService
import com.ourclassbank.coredomain.service.creditscore.CreditScoreService
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

@Tag(name = "신용평가")
@RestController
class CreditScoreController(
    private val creditScoreService: CreditScoreService,
    private val creditScoreReadService: CreditScoreReadService
) {
    @Operation(summary = "개인 회원 평가", description = "- auth: CREDIT_SCORE_MANAGER")
    @PostMapping("/api/v1/credit-score/{userLoginId}")
    fun update(@PathVariable userLoginId: String, @RequestBody request: CreditScoreUpdateRequest): CreditScoreUpdateResponse {
        return creditScoreService.update(request.toVo(userLoginId)).run {
            CreditScoreUpdateResponse.from(this)
        }
    }

    @Operation(summary = "이력 조회", description = "- auth: CREDIT_SCORE_MANAGER")
    @GetMapping("/api/v1/credit-score")
    fun findAll(
        @RequestParam userLoginId: String,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) fromAt: LocalDateTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) toAt: LocalDateTime
    ): List<CreditScoreHistoryResponse> {
        return creditScoreReadService.findAllHistoryByUser(userLoginId, fromAt, toAt).run {
            this.map { CreditScoreHistoryResponse.from(it) }
        }
    }
}
