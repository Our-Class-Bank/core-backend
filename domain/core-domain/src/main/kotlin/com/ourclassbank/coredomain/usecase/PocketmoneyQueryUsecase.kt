package com.ourclassbank.coredomain.usecase

import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory
import java.time.LocalDateTime

interface PocketmoneyQueryUsecase {
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

    fun findAllHistory(
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory>
}
