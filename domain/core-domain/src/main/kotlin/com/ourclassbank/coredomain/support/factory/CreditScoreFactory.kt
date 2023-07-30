package com.ourclassbank.coredomain.support.factory

import com.ourclassbank.coredb.entity.CreditEvaluationHistoryEntity
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluationHistory

fun CreditEvaluationHistoryEntity.toModel(): CreditEvaluationHistory {
    return CreditEvaluationHistory(
        id = id!!,
        username = username,
        changePoint = changePoint,
        description = description,
        score = score,
        createdAt = createdAt!!,
    )
}
