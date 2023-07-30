package com.ourclassbank.coreapi.controller.common

import com.ourclassbank.modeldomain.user.creditscore.CreditScoreHistory
import java.time.LocalDateTime

data class CreditScoreHistoryResponse(
    val id: Long,
    val userLoginId: String,
    val changePoint: Int,
    val description: String,
    val score: Int,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(creditScoreHistory: CreditScoreHistory): CreditScoreHistoryResponse {
            return creditScoreHistory.run {
                CreditScoreHistoryResponse(
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
