package com.ourclassbank.coredomain.support.factory

import com.ourclassbank.coredb.entity.CreditScoreHistoryEntity
import com.ourclassbank.modeldomain.user.creditscore.CreditScoreHistory

fun CreditScoreHistoryEntity.toModel(): CreditScoreHistory {
    return CreditScoreHistory(
        id = id!!,
        userLoginId = userLoginId,
        changePoint = changePoint,
        description = description,
        score = score,
        createdAt = createdAt!!,
    )
}
