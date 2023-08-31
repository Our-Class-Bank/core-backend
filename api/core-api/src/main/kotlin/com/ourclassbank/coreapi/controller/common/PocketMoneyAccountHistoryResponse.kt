package com.ourclassbank.coreapi.controller.common

import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import java.time.LocalDateTime

data class PocketMoneyAccountHistoryResponse(
    val accountNo: String,
    val type: PocketmoneyAccountHistoryType,
    val amount: Long,
    val description: String,
    val balance: Long,
    val transactionAt: LocalDateTime,
    val createUser: CreateUser
) {
    constructor(history: PocketmoneyAccountHistory) : this(
        accountNo = history.accountNo,
        type = history.type,
        amount = history.amount,
        description = history.description,
        balance = history.balance,
        transactionAt = history.transactionAt,
        createUser = CreateUser(history.createUser.username, history.createUser.name)
    )

    data class CreateUser(
        val username: String,
        val name: String
    )
}
