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
        targetLoginId: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    ) {
        pocketmoneyAccountHistoryRepository.save(
            targetLoginId = targetLoginId,
            type = type,
            amount = amount,
            description = description
        )
    }

    fun withdraw(
        targetLoginId: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    ) {
        pocketmoneyAccountHistoryRepository.save(
            targetLoginId = targetLoginId,
            type = type,
            amount = amount,
            description = description
        )
    }

    @Transactional(readOnly = true)
    fun findAllByLoginId(loginId: String, fromAt: LocalDateTime, toAt: LocalDateTime): List<PocketmoneyAccountHistory> {
        return pocketmoneyAccountHistoryRepository.findAllByLoginId(loginId, fromAt, toAt)
    }

    // todo 실행자에 대한 기능을 추가한 후 구현
    @Transactional(readOnly = true)
    fun findAllByExecutorLoginId(executorLoginId: String, fromAt: LocalDateTime, toAt: LocalDateTime) {
        TODO()
    }
}
