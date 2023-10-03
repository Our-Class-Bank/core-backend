package com.ourclassbank.coreapi.controller.creditevaluation.response

import com.ourclassbank.coreapi.controller.common.CreditEvaluationHistoryResponse
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluationHistory

data class CreditEvaluateResetResponse(
    val history: CreditEvaluationHistoryResponse
) {
    companion object {
        fun from(history: CreditEvaluationHistory): CreditEvaluateResetResponse {
            return CreditEvaluateResetResponse(CreditEvaluationHistoryResponse(history))
        }
    }
}
