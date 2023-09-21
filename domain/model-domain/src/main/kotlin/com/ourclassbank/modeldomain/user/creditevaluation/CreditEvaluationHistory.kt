package com.ourclassbank.modeldomain.user.creditevaluation

import java.time.LocalDateTime

data class CreditEvaluationHistory(
    val id: Long,
    val username: String,
    val changePoint: Int,
    val description: String,
    val score: Int,

    val transactionAt: LocalDateTime,
    val executeUsername: String
)
