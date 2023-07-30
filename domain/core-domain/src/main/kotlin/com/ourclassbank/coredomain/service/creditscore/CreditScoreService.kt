package com.ourclassbank.coredomain.service.creditscore

import com.ourclassbank.coredomain.repository.CreditScoreRepository
import com.ourclassbank.modeldomain.user.creditscore.CreditScoreHistory
import com.ourclassbank.modeldomain.user.creditscore.CreditScoreUpdateVo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CreditScoreService(
    private val creditScoreRepository: CreditScoreRepository
) {
    fun update(updateVo: CreditScoreUpdateVo): CreditScoreHistory {
        return updateVo.run {
            creditScoreRepository.update(updateVo)
        }
    }
}
