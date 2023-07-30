package com.ourclassbank.coredomain.service.creditscore

import com.ourclassbank.coredomain.repository.CreditScoreRepository
import com.ourclassbank.modeldomain.user.creditscore.CreditScoreHistory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CreditScoreReadService(
    private val creditScoreRepository: CreditScoreRepository
) {
    /**
     * 신용평가 최종 이력을 조회합니다.
     */
    fun findLastHistoryByUserLoginId(userLoginId: String): CreditScoreHistory {
        return creditScoreRepository.findLastHistoryByUserLoginId(userLoginId)
    }
}
