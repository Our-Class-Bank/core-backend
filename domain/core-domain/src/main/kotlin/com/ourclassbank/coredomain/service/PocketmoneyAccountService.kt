package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.PocketmoneyAccountHistoryRepository
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class PocketmoneyAccountService(
    private val pocketmoneyAccountHistoryRepository: PocketmoneyAccountHistoryRepository
) {
    fun deposit(
        accountNo: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    ) {
        pocketmoneyAccountHistoryRepository.save(
            accountNo = accountNo,
            type = type,
            amount = amount,
            description = description
        )
    }

    fun withdraw(
        accountNo: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    ) {
        pocketmoneyAccountHistoryRepository.save(
            accountNo = accountNo,
            type = type,
            amount = amount,
            description = description
        )
    }

    @Transactional(readOnly = true)
    fun findAllHistoryByAccountNo(
        accountNo: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return pocketmoneyAccountHistoryRepository.findAllByAccountNo(accountNo, fromAt, toAt)
    }

    @Transactional(readOnly = true)
    fun findAllHistoryByCreatedBy(
        createdBy: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return pocketmoneyAccountHistoryRepository.findAllByCreatedBy(createdBy, fromAt, toAt)
    }
}
