package com.ourclassbank.coreapi.controller.creditscore.response

import com.ourclassbank.coreapi.controller.common.CreditScoreHistoryResponse
import com.ourclassbank.modeldomain.user.creditscore.CreditScoreHistory

data class CreditScoreUpdateResponse(
    val creditScoreHistory: CreditScoreHistoryResponse
) {
    companion object {
        fun from(creditScoreHistory: CreditScoreHistory): CreditScoreUpdateResponse {
            return CreditScoreHistoryResponse.from(creditScoreHistory).run {
                CreditScoreUpdateResponse(this)
            }
        }
    }
}
