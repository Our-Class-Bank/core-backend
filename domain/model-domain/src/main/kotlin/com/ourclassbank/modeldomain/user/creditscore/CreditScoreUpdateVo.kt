package com.ourclassbank.modeldomain.user.creditscore

data class CreditScoreUpdateVo(
    val userLoginId: String,
    val changePoint: Int,
    val description: String,
)
