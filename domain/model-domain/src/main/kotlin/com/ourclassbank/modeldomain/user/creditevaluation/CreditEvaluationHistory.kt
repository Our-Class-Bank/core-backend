package com.ourclassbank.modeldomain.user.creditevaluation

import java.time.LocalDateTime

data class CreditEvaluationHistory(
    val id: Long,
    val userLoginId: String,
    val changePoint: Int,
    val description: String,
    val score: Int,
    val createdAt: LocalDateTime,
)
