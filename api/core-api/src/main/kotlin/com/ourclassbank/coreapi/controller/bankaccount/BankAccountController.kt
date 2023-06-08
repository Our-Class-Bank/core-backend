package com.ourclassbank.coreapi.controller.bankaccount

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*

@Tag(name = "은행 계좌", description = "한정된 기능만 작동되는 mock api 입니다.")
@RestController
class BankAccountController {
    val bankAccounts = HashMap<String, Long>()

    @Operation(summary = "생성", description = "userId 는 검증하지 않습니다.")
    @PostMapping("/bank-account/create")
    fun create(@RequestBody request: BankAccountCreateRequest): BankAccountCreateResponse {
        val accountCode = UUID.randomUUID().toString()
        bankAccounts[accountCode] = 0L
        return BankAccountCreateResponse(accountCode)
    }

    @Operation(summary = "입금", description = "존재하지 않는 계좌번호는 에러가 발생합니다.")
    @PostMapping("/bank-account/deposit")
    fun deposit(@RequestBody request: BankAccountDepositRequest): BankAccountDepositResponse {
        val amount = bankAccounts[request.accountCode] ?: throw IllegalArgumentException("계좌가 존재하지 않습니다.")
        bankAccounts[request.accountCode] = amount + request.amount
        return BankAccountDepositResponse(request.accountCode, amount + request.amount)
    }

    @Operation(summary = "출금", description = "존재하지 않는 계좌번호는 에러가 발생합니다.")
    @PostMapping("/bank-account/withdraw")
    fun withdraw(@RequestBody request: BankAccountWithdrawRequest): BankAccountWithdrawResponse {
        val amount = bankAccounts[request.accountCode] ?: throw IllegalArgumentException("계좌가 존재하지 않습니다.")
        if (amount < request.amount) {
            throw IllegalArgumentException("잔액이 부족합니다.")
        }
        bankAccounts[request.accountCode] = amount - request.amount
        return BankAccountWithdrawResponse(request.accountCode, request.amount)
    }

    @Operation(summary = "조회", description = "최종 잔액만 조회되게 구현되어 있습니다.")
    @GetMapping("/bank-account")
    fun read(
        @RequestParam accountCode: String,
        @RequestParam startAt: LocalDateTime,
        @RequestParam endAt: LocalDateTime
    ): List<BankAccountHistoryResponse> {
        return listOf(
            BankAccountHistoryResponse(
                LocalDateTime.now(),
                bankAccounts[accountCode] ?: throw IllegalArgumentException("계좌가 존재하지 않습니다.")
            )
        )
    }
}


data class BankAccountCreateRequest(
    val userId: String
)

data class BankAccountCreateResponse(
    val accountCode: String
)

data class BankAccountDepositRequest(
    val accountCode: String,
    val amount: Long
)

data class BankAccountDepositResponse(
    val accountCode: String,
    val currentAmount: Long,
)

data class BankAccountWithdrawRequest(
    val accountCode: String,
    val amount: Long
)

data class BankAccountWithdrawResponse(
    val accountCode: String,
    val currentAmount: Long,
)

data class BankAccountHistoryResponse(
    val transactionAt: LocalDateTime,
    val amount: Long,
)
