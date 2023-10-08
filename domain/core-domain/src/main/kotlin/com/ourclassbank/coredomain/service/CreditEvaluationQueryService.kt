package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.CreditEvaluationRepository
import com.ourclassbank.coredomain.usecase.CreditEvaluationQueryUsecase
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluationHistory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional(readOnly = true)
@Service
class CreditEvaluationQueryService(
    private val repository: CreditEvaluationRepository
) : CreditEvaluationQueryUsecase {
    /**
     * 신용평가 최종 이력을 조회합니다.
     */
    override fun findLastHistoryByUser(username: String): CreditEvaluationHistory {
        return repository.findLastHistoryByUser(username)
            ?: throw IllegalArgumentException("신용평가 이력이 존재하지 않는 회원")
    }

    override fun findAllHistoryByUser(username: String, fromAt: LocalDateTime, toAt: LocalDateTime): List<CreditEvaluationHistory> {
        return repository.findAllHistoryByUser(username, fromAt, toAt)
    }

    override fun findAllHistoryByCreatedBy(
        createdBy: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<CreditEvaluationHistory> {
        return repository.findAllByCreatedBy(createdBy, fromAt, toAt)
    }

    override fun findAllHistory(fromAt: LocalDateTime, toAt: LocalDateTime): List<CreditEvaluationHistory> {
        return repository.findAll(fromAt, toAt)
    }

    override fun readCurrentScore(username: String): Int {
        return repository.readCurrentScore(username)
    }
}
