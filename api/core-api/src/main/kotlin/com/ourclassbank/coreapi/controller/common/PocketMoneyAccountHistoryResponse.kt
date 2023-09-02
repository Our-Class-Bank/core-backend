package com.ourclassbank.coreapi.controller.common

import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import java.time.LocalDateTime

data class PocketMoneyAccountHistoryResponse(
    val accountNo: String,
    val owner: UserResponse,
    val type: PocketmoneyAccountHistoryType,
    val amount: Long,
    val description: String,
    val balance: Long,
    val transactionAt: LocalDateTime,
    val executor: UserResponse
) {
    constructor(history: PocketmoneyAccountHistory) : this(
        accountNo = history.accountNo,
        owner = history.owner.run { UserResponse(username, name) },
        type = history.type,
        amount = history.amount,
        description = history.description,
        balance = history.balance,
        transactionAt = history.transactionAt,
        executor = history.executor.run { UserResponse(username, name) }
    )

    data class UserResponse(
        val username: String,
        val name: String
    )
}
