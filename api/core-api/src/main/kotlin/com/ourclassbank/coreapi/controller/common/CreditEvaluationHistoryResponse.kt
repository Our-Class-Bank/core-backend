package com.ourclassbank.coreapi.controller.common

import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluationHistory
import java.time.LocalDateTime

data class CreditEvaluationHistoryResponse(
    val id: Long,
    val username: String,
    val changePoint: Int,
    val description: String,
    val score: Int,

    val transactionAt: LocalDateTime,
    val executeUsername: String
) {
    constructor(history: CreditEvaluationHistory) : this(
        id = history.id,
        username = history.username,
        changePoint = history.changePoint,
        description = history.description,
        score = history.score,
        transactionAt = history.transactionAt,
        executeUsername = history.executeUsername
    )
}
