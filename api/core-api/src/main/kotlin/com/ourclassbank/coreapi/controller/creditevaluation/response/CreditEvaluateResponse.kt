package com.ourclassbank.coreapi.controller.creditevaluation.response

import com.ourclassbank.coreapi.controller.common.CreditEvaluationHistoryResponse
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluationHistory

data class CreditEvaluateResponse(
    val creditEvaluationHistory: CreditEvaluationHistoryResponse
) {
    companion object {
        fun from(creditEvaluationHistory: CreditEvaluationHistory): CreditEvaluateResponse {
            return CreditEvaluationHistoryResponse(creditEvaluationHistory).run {
                CreditEvaluateResponse(this)
            }
        }
    }
}
