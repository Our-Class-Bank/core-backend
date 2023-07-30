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
    fun findLastHistoryByUserLoginId(userLoginId: String): CreditEvaluationHistory {
        return repository.findLastHistoryByUserLoginId(userLoginId)
    }

    fun findAllHistoryByUser(userLoginId: String, fromAt: LocalDateTime, toAt: LocalDateTime): List<CreditEvaluationHistory> {
        return repository.findAllHistoryByUser(userLoginId, fromAt, toAt)
    }
}
