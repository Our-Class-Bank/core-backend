package com.ourclassbank.coredomain.support.factory

import com.ourclassbank.coredb.entity.PocketmoneyAccountHistoryEntity
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory

fun PocketmoneyAccountHistory.toEntity(loginId: String, balance: Long): PocketmoneyAccountHistoryEntity {
    return PocketmoneyAccountHistoryEntity(
        type = this.type,
        amount = this.amount,
        description = this.description,
        balance = balance,
        loginId = loginId
    )
}

fun PocketmoneyAccountHistoryEntity.toModel(): PocketmoneyAccountHistory {
    return PocketmoneyAccountHistory(
        type = this.type,
        amount = this.amount,
        description = this.description,
        balance = this.balance,
        loginId = this.loginId,
        transactionAt = this.createdAt!!
    )
}
