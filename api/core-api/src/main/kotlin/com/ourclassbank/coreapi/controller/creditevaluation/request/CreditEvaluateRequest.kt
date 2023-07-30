package com.ourclassbank.coreapi.controller.creditevaluation.request

import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluateVo

data class CreditEvaluateRequest(
    val changePoint: Int,
    val description: String
) {
    fun toVo(username: String): CreditEvaluateVo {
        return CreditEvaluateVo(
            username = username,
            changePoint = changePoint,
            description = description,
        )
    }
}
