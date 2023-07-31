package com.ourclassbank.coredomain.repository.strategy

import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.coredomain.support.exception.DomainExceptionType.INVALID_CREDIT_EVALUATION_SCORE
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluateVo
import org.springframework.stereotype.Component

@Component
class CreditEvaluateRepositoryValidationStrategy {
    fun validate(evaluateVo: CreditEvaluateVo, evaluateScore: Int) {
        신용평가점수_범위는_0_to_100(evaluateScore)
    }

    private fun 신용평가점수_범위는_0_to_100(evaluateScore: Int) {
        if (evaluateScore < 0 || evaluateScore > 100) throw DomainException(INVALID_CREDIT_EVALUATION_SCORE)
    }
}
