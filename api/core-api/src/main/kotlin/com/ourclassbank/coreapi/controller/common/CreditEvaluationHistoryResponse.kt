package com.ourclassbank.coreapi.controller.common

import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluationHistory
import java.time.LocalDateTime

data class CreditEvaluationHistoryResponse(
    val id: Long,
    val userLoginId: String,
    val changePoint: Int,
    val description: String,
    val score: Int,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(creditEvaluationHistory: CreditEvaluationHistory): CreditEvaluationHistoryResponse {
            return creditEvaluationHistory.run {
                CreditEvaluationHistoryResponse(
                    id = id,
                    userLoginId = userLoginId,
                    changePoint = changePoint,
                    description = description,
                    score = score,
                    createdAt = createdAt
                )
            }
        }
    }
}
