package com.ourclassbank.coredomain.usecase

import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType

interface PocketmoneyCommandUsecase {
    fun deposit(
        accountNo: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    )

    fun withdraw(
        accountNo: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    )
}
