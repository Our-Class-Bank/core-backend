package com.ourclassbank.coreapi.controller.account.pocketmoney

import com.ourclassbank.coreapi.controller.account.pocketmoney.request.PocketmoneyAccountDepositRequest
import com.ourclassbank.coreapi.controller.account.pocketmoney.request.PocketmoneyAccountWithdrawRequest
import com.ourclassbank.coreapi.controller.common.PocketMoneyAccountHistoryResponse
import com.ourclassbank.coredomain.service.PocketmoneyAccountService
import com.ourclassbank.coredomain.support.security.UserContext
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@Tag(name = "용돈계좌", description = "auth: BANKER")
@RestController
class PocketmoneyAccountController(
    private val pocketmoneyAccountService: PocketmoneyAccountService
) {
    @Operation(summary = "입금")
    @PostMapping("/api/v1/account/pocketmoney/deposit")
    fun deposit(@RequestBody request: PocketmoneyAccountDepositRequest) {
        pocketmoneyAccountService.deposit(
            request.accountNo,
            request.type,
            request.amount,
            request.description
        )
    }

    @Operation(summary = "출금")
    @PostMapping("/api/v1/account/pocketmoney/withdraw")
    fun withdraw(@RequestBody request: PocketmoneyAccountWithdrawRequest) {
        pocketmoneyAccountService.withdraw(
            request.accountNo,
            request.type,
            request.amount,
            request.description
        )
    }

    @Operation(summary = "입출금 기록 조회 execute by banker", description = "- order by createdAt desc")
    @GetMapping("/api/v1/account/pocketmoney/history/by-banker")
    fun findAllHistoryByBanker(
        @RequestParam fromAt: LocalDateTime,
        @RequestParam toAt: LocalDateTime
    ): List<PocketMoneyAccountHistoryResponse> {
        val userContext = SecurityContextHolder.getContext().authentication.principal as UserContext
        return pocketmoneyAccountService.findAllHistoryByCreatedBy(
            createdBy = userContext.uUsername,
            fromAt = fromAt,
            toAt = toAt
        ).map { PocketMoneyAccountHistoryResponse(it) }
    }

    @Operation(summary = "입출금 기록 조회 - auth: STUDENT", description = "- order by createdAt desc")
    @GetMapping("/api/v1/account/pocketmoney/history/{accountNo}")
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


