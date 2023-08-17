package com.ourclassbank.coreapi.controller.my

import com.ourclassbank.coreapi.controller.common.AccountResponse
import com.ourclassbank.coreapi.controller.common.CreditEvaluationHistoryResponse
import com.ourclassbank.coreapi.controller.common.PocketMoneyAccountHistoryResponse
import com.ourclassbank.coreapi.controller.common.UserResponse
import com.ourclassbank.coreapi.controller.my.response.MyInfoResponse
import com.ourclassbank.coredomain.support.security.UserContext
import com.ourclassbank.coredomain.usecase.CreditEvaluationQueryUsecase
import com.ourclassbank.coredomain.usecase.PocketmoneyAccountUsecase
import com.ourclassbank.coredomain.usecase.UserQueryUsecase
import com.ourclassbank.modeldomain.common.AccountType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@Tag(name = "내 정보", description = "auth: STUDENT")
@RestController
class MyController(
    private val userQueryUsecase: UserQueryUsecase,
    private val creditEvaluationQueryUsecase: CreditEvaluationQueryUsecase,
    private val pocketmoneyAccountUsecase: PocketmoneyAccountUsecase
) {
    @Operation(summary = "내 계좌 목록 조회", description = "현재 존재하는 계좌는 용돈계좌뿐 입니다.")
    @GetMapping("/api/v1/my/account")
    fun findAllMyAccount(): List<AccountResponse> {
        val userContext = getUserContext()
        val pocketmoneyAccount = userQueryUsecase.findByUsername(userContext.uUsername).run {
            AccountResponse(AccountType.POCKETMONEY, pocketmoneyAccountNo)
        }

        return listOf(pocketmoneyAccount)
    }

    @Operation(summary = "내 정보 조회", description = "현재 존재하는 계좌는 용돈계좌뿐 입니다.")
    @GetMapping("/api/v1/my")
    fun readMyInfo(): MyInfoResponse {
        val userContext = getUserContext()
        return userQueryUsecase.findByUsername(userContext.uUsername).run {
            MyInfoResponse(user = UserResponse(this))
        }
    }

    @Operation(summary = "내 신용평가 이력 조회", description = "- order by createdAt desc")
    @GetMapping("/api/v1/my/credit-evaluation/history")
    fun findAllMyCreditEvaluationHistory(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) fromAt: LocalDateTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) toAt: LocalDateTime
    ): List<CreditEvaluationHistoryResponse> {
        val userContext = getUserContext()
        return creditEvaluationQueryUsecase.findAllHistoryByUser(userContext.uUsername, fromAt, toAt)
            .map { CreditEvaluationHistoryResponse.from(it) }
    }

    @Operation(
        summary = "내 계좌 입출금 이력 조회", description = "- order by createdAt desc\n" +
                "- 현재 존재하는 계좌는 용돈계좌뿐 입니다."
    )
    @GetMapping("/api/v1/my/account/history/{accountNo}")
    fun findAllMyPocketmoneyAccountHistory(
        @PathVariable accountNo: String,
        @RequestParam fromAt: LocalDateTime,
        @RequestParam toAt: LocalDateTime
    ): List<PocketMoneyAccountHistoryResponse> {
        val userContext = getUserContext()
        userQueryUsecase.findByUsername(userContext.uUsername).run {
            require(pocketmoneyAccountNo == accountNo) { "다른 사람의 계좌번호 입니다." }
        }

        return pocketmoneyAccountUsecase.findAllHistoryByAccountNo(
            accountNo = accountNo,
            fromAt = fromAt,
            toAt = toAt
        ).map { PocketMoneyAccountHistoryResponse(it) }
    }

    private fun getUserContext(): UserContext {
        return SecurityContextHolder.getContext().authentication.principal as UserContext
    }
}
