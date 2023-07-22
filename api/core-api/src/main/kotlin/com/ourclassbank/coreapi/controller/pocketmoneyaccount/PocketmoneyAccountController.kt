package com.ourclassbank.coreapi.controller.pocketmoneyaccount

import com.ourclassbank.coreapi.controller.common.PocketMoneyAccountHistoryResponse
import com.ourclassbank.coredomain.service.PocketmoneyAccountService
import com.ourclassbank.coredomain.support.security.UserContext
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*

@Tag(name = "용돈계좌")
@RestController
class PocketmoneyAccountController(
    private val pocketmoneyAccountService: PocketmoneyAccountService
) {
    @Operation(summary = "입금", description = "- auth: banker")
    @PostMapping("/api/v1/account/pocketmoney/deposit")
    fun deposit(@RequestBody request: PocketmoneyAccountDepositRequest) {
        pocketmoneyAccountService.deposit(
            request.accountNo,
            request.type,
            request.amount,
            request.description
        )
    }

    @Operation(summary = "출금", description = "- auth: banker")
    @PostMapping("/api/v1/account/pocketmoney/withdraw")
    fun withdraw(@RequestBody request: PocketmoneyAccountWithdrawRequest) {
        pocketmoneyAccountService.withdraw(
            request.accountNo,
            request.type,
            request.amount,
            request.description
        )
    }

    @Operation(summary = "BANKER 가 수행한 입출금 기록 조회", description = "- auth: banker\n- order by createdAt desc")
    @GetMapping("/api/v1/account/pocketmoney-history/by-banker")
    fun findAllHistoryByBanker(
        @RequestParam fromAt: LocalDateTime,
        @RequestParam toAt: LocalDateTime
    ): List<PocketMoneyAccountHistoryResponse> {
        val userContext = SecurityContextHolder.getContext().authentication.principal as UserContext
        return pocketmoneyAccountService.findAllHistoryByCreatedBy(
            createdBy = userContext.loginId,
            fromAt = fromAt,
            toAt = toAt
        ).map { PocketMoneyAccountHistoryResponse(it) }
    }

    @Operation(summary = "입출금 기록 조회", description = "- order by createdAt desc")
    @GetMapping("/api/v1/account/pocketmoney-history/{accountNo}")
    fun findAllHistory(
        @PathVariable accountNo: String,
        @RequestParam fromAt: LocalDateTime,
        @RequestParam toAt: LocalDateTime
    ): List<PocketMoneyAccountHistoryResponse> {
        return pocketmoneyAccountService.findAllHistoryByAccountNo(
            accountNo = accountNo,
            fromAt = fromAt,
            toAt = toAt
        ).map { PocketMoneyAccountHistoryResponse(it) }
    }
}

data class PocketmoneyAccountDepositRequest(
    val accountNo: String,
    val type: PocketmoneyAccountHistoryType,
    val amount: Long,
    val description: String
)

data class PocketmoneyAccountWithdrawRequest(
    val accountNo: String,
    val type: PocketmoneyAccountHistoryType,
    val amount: Long,
    val description: String
)
