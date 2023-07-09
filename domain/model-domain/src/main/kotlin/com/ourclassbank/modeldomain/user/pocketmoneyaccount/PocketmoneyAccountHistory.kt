package com.ourclassbank.modeldomain.user.pocketmoneyaccount

import java.time.LocalDateTime

data class PocketmoneyAccountHistory(
    val type: PocketmoneyAccountHistoryType,

    val amount: Long,
    val description: String,
    val balance: Long,

    val loginId: String,
    val transactionAt: LocalDateTime? = null,
)
