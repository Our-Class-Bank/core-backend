package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.PocketmoneyAccountQueryRepository
import com.ourclassbank.coredomain.usecase.PocketmoneyQueryUsecase
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional(readOnly = true)
@Service
class PocketmoneyQueryService(
    private val queryRepository: PocketmoneyAccountQueryRepository
) : PocketmoneyQueryUsecase {
    override fun findAllHistoryByAccountNo(
        accountNo: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return queryRepository.findAllByAccountNo(accountNo, fromAt, toAt)
    }

    override fun findAllHistoryByCreatedBy(
        createdBy: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return queryRepository.findAllByCreatedBy(createdBy, fromAt, toAt)
    }

    override fun findAllHistory(fromAt: LocalDateTime, toAt: LocalDateTime): List<PocketmoneyAccountHistory> {
        return queryRepository.findAll(fromAt, toAt)
    }
}
