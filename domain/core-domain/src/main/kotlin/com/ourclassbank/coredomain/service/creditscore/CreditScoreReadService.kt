package com.ourclassbank.coredomain.service.creditscore

import com.ourclassbank.coredomain.repository.CreditScoreRepository
import com.ourclassbank.modeldomain.user.creditscore.CreditScoreHistory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional(readOnly = true)
@Service
class CreditScoreReadService(
    private val repository: CreditScoreRepository
) {
    /**
     * 신용평가 최종 이력을 조회합니다.
     */
    fun findLastHistoryByUserLoginId(userLoginId: String): CreditScoreHistory {
        return repository.findLastHistoryByUserLoginId(userLoginId)
    }

    fun findAllHistoryByUser(userLoginId: String, fromAt: LocalDateTime, toAt: LocalDateTime): List<CreditScoreHistory> {
        return repository.findAllHistoryByUser(userLoginId, fromAt, toAt)
    }
}
