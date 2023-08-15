package com.ourclassbank.coredomain.service.pocketmoney

import com.ourclassbank.coredomain.repository.PocketmoneyAccountHistoryRepository
import com.ourclassbank.coredomain.usecase.PocketmoneyAccountUsecase
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class PocketmoneyAccountService(
    private val repository: PocketmoneyAccountHistoryRepository
) : PocketmoneyAccountUsecase {
    @Transactional
    override fun deposit(
        accountNo: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    ) {
        repository.save(
            accountNo = accountNo,
            type = type,
            amount = amount,
            description = description
        )
    }

    @Transactional
    override fun withdraw(
        accountNo: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    ) {
        repository.save(
            accountNo = accountNo,
            type = type,
            amount = amount,
            description = description
        )
    }

    @Transactional(readOnly = true)
    override fun findAllHistoryByAccountNo(
        accountNo: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return repository.findAllByAccountNo(accountNo, fromAt, toAt)
    }

    @Transactional(readOnly = true)
    override fun findAllHistoryByCreatedBy(
        createdBy: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return repository.findAllByCreatedBy(createdBy, fromAt, toAt)
    }
}
