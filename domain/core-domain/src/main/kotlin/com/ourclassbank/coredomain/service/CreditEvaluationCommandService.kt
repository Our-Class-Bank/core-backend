package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.CreditEvaluationRepository
import com.ourclassbank.coredomain.usecase.CreditEvaluationCommandUsecase
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluateResetVo
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

    override fun reset(vo: CreditEvaluateResetVo): CreditEvaluationHistory {
        return CreditEvaluateVo(
            username = vo.username,
            changePoint = creditEvaluationRepository.findLastHistoryByUser(vo.username)?.let { vo.basePoint - it.score } ?: vo.basePoint,
            description = vo.description
        ).run {
            creditEvaluationRepository.evaluate(this)
        }
    }
}
