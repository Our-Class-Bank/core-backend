package com.ourclassbank.coreapi.controller.pocketmoneyaccount

import com.ourclassbank.coredomain.service.PocketmoneyAccountService
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import java.util.*

@Tag(name = "용돈계좌")
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
