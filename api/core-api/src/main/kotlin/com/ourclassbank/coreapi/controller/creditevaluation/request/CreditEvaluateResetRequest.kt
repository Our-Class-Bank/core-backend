package com.ourclassbank.coreapi.controller.creditevaluation.request

import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluateResetVo

data class CreditEvaluateResetRequest(
    val basePoint: Int,
    val description: String
) {
    fun toVo(username: String): CreditEvaluateResetVo {
        return CreditEvaluateResetVo(
            username = username,
            basePoint = basePoint,
            description = description,
        )
    }
}
