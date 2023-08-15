package com.ourclassbank.coredomain.service.creditevaluation

import com.ourclassbank.coredomain.repository.CreditEvaluationRepository
import com.ourclassbank.coredomain.usecase.CreditEvaluationCommandUsecase
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluateVo
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluationHistory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CreditEvaluationCommandService(
    private val creditEvaluationRepository: CreditEvaluationRepository
) : CreditEvaluationCommandUsecase {
    override fun evaluate(evaluateVo: CreditEvaluateVo): CreditEvaluationHistory {
        return evaluateVo.run {
            creditEvaluationRepository.evaluate(evaluateVo)
        }
    }
}
