package com.ourclassbank.modeldomain.user.creditscore

import java.time.LocalDateTime

data class CreditScoreHistory(
    val id: Long,
    val userLoginId: String,
    val changePoint: Int,
    val description: String,
    val score: Int,
    val createdAt: LocalDateTime,
)
