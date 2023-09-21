package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.PocketmoneyAccountCommandRepository
import com.ourclassbank.coredomain.repository.PocketmoneyAccountQueryRepository
import com.ourclassbank.coredomain.usecase.PocketmoneyAccountUsecase
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class PocketmoneyAccountService(
    private val commandRepository: PocketmoneyAccountCommandRepository,
    private val queryRepository: PocketmoneyAccountQueryRepository
) : PocketmoneyAccountUsecase {
    @Transactional
    override fun deposit(
        accountNo: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    ) {
        commandRepository.save(
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
        commandRepository.save(
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
        return queryRepository.findAllByAccountNo(accountNo, fromAt, toAt)
    }

    @Transactional(readOnly = true)
    override fun findAllHistoryByCreatedBy(
        createdBy: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return queryRepository.findAllByCreatedBy(createdBy, fromAt, toAt)
    }
}
