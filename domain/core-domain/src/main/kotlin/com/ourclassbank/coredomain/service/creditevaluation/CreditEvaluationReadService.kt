package com.ourclassbank.coredomain.service.creditevaluation

import com.ourclassbank.coredomain.repository.CreditEvaluationRepository
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluationHistory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional(readOnly = true)
@Service
class CreditEvaluationReadService(
    private val repository: CreditEvaluationRepository
) {
    /**
     * 신용평가 최종 이력을 조회합니다.
     */
    fun findLastHistoryByUser(username: String): CreditEvaluationHistory {
        return repository.findLastHistoryByUser(username)
    }

    fun findAllHistoryByUser(username: String, fromAt: LocalDateTime, toAt: LocalDateTime): List<CreditEvaluationHistory> {
        return repository.findAllHistoryByUser(username, fromAt, toAt)
    }

    fun findAllHistoryByCreatedBy(
        createdBy: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<CreditEvaluationHistory> {
        return repository.findAllByCreatedBy(createdBy, fromAt, toAt)
    }

    fun readCurrentScore(username: String): Int {
        return repository.readCurrentScore(username)
    }
}
