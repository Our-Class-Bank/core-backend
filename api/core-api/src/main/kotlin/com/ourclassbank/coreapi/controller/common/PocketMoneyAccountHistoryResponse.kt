package com.ourclassbank.coreapi.controller.common

import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import java.time.LocalDateTime

data class PocketMoneyAccountHistoryResponse(
    val accountNo: String,
    val ownerUsername: String,
    val type: PocketmoneyAccountHistoryType,
    val amount: Long,
    val description: String,
    val balance: Long,
    val transactionAt: LocalDateTime,
    val executeUsername: String
) {
    constructor(history: PocketmoneyAccountHistory) : this(
        accountNo = history.accountNo,
        ownerUsername = history.ownerUsername,
        type = history.type,
        amount = history.amount,
        description = history.description,
        balance = history.balance,
        transactionAt = history.transactionAt,
        executeUsername = history.executeUsername
    )
}
