package com.ourclassbank.modeldomain.user.pocketmoneyaccount

import java.time.LocalDateTime

data class PocketmoneyAccountHistory(
    val accountNo: String,
    val type: PocketmoneyAccountHistoryType,

    val amount: Long,
    val description: String,
    val balance: Long,

    val transactionAt: LocalDateTime? = null,
)
