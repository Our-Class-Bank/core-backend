package com.ourclassbank.coredomain.support.factory

import com.ourclassbank.coredb.entity.PocketmoneyAccountHistoryEntity
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory

fun PocketmoneyAccountHistory.toEntity(balance: Long): PocketmoneyAccountHistoryEntity {
    return PocketmoneyAccountHistoryEntity(
        accountNo = this.accountNo,
        type = this.type,
        amount = this.amount,
        description = this.description,
        balance = balance,
    )
}

fun PocketmoneyAccountHistoryEntity.toModel(): PocketmoneyAccountHistory {
    return PocketmoneyAccountHistory(
        accountNo = this.accountNo,
        type = this.type,
        amount = this.amount,
        description = this.description,
        balance = this.balance,
        transactionAt = this.createdAt!!
    )
}
