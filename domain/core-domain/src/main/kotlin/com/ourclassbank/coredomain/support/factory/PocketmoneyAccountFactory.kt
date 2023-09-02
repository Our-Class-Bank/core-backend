package com.ourclassbank.coredomain.support.factory

import com.ourclassbank.coredb.entity.PocketmoneyAccountHistoryEntity
import com.ourclassbank.coredb.entity.UserEntity
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

fun PocketmoneyAccountHistoryEntity.toModel(executeUserEntity: UserEntity): PocketmoneyAccountHistory {
    return PocketmoneyAccountHistory(
        accountNo = this.accountNo,
        type = this.type,
        amount = this.amount,
        description = this.description,
        balance = this.balance,
        transactionAt = this.createdAt!!,
        executor = PocketmoneyAccountHistory.User(
            username = executeUserEntity.username,
            name = executeUserEntity.name,
        )
    )
}
