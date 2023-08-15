package com.ourclassbank.coredomain.usecase

import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import java.time.LocalDateTime

interface PocketmoneyAccountUsecase {
    fun deposit(
        accountNo: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    )

    fun withdraw(
        accountNo: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    )

    fun findAllHistoryByAccountNo(
        accountNo: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory>

    fun findAllHistoryByCreatedBy(
        createdBy: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory>
}
