package com.ourclassbank.coreapi.controller.account.pocketmoney.request

import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType

data class PocketmoneyAccountWithdrawRequest(
    val accountNo: String,
    val type: PocketmoneyAccountHistoryType,
    val amount: Long,
    val description: String
)
