package com.ourclassbank.coreapi.controller.creditscore.request

import com.ourclassbank.modeldomain.user.creditscore.CreditScoreUpdateVo

data class CreditScoreUpdateRequest(
    val changePoint: Int,
    val description: String
) {
    fun toVo(userLoginId: String): CreditScoreUpdateVo {
        return CreditScoreUpdateVo(
            userLoginId = userLoginId,
            changePoint = changePoint,
            description = description,
        )
    }
}
