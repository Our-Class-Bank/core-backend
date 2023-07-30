package com.ourclassbank.coreapi.controller.creditevaluation.request

import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluateVo

data class CreditEvaluateRequest(
    val changePoint: Int,
    val description: String
) {
    fun toVo(userLoginId: String): CreditEvaluateVo {
        return CreditEvaluateVo(
            userLoginId = userLoginId,
            changePoint = changePoint,
            description = description,
        )
    }
}
