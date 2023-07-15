package com.ourclassbank.coredomain.repository

import com.ourclassbank.coredb.dao.PocketmoneyAccountHistoryEntityJpaDao
import com.ourclassbank.coredb.entity.PocketmoneyAccountHistoryEntity
import com.ourclassbank.coredomain.support.factory.toModel
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class PocketmoneyAccountHistoryRepository(
    private val pocketmoneyAccountHistoryEntityJpaDao: PocketmoneyAccountHistoryEntityJpaDao,
) {
    fun save(
        accountNo: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String
    ) {
        PocketmoneyAccountHistoryEntity(
            accountNo = accountNo,
            type = type,
            amount = amount,
            description = description,
            balance = getBalance(accountNo),
        ).also {
            pocketmoneyAccountHistoryEntityJpaDao.save(it)
        }
    }

    // todo 마지막 history 의 balance 를 가져와서 반환하도록 수정
    private fun getBalance(accountNo: String): Long {
        return 0L
    }

    fun findAllByAccountNo(
        accountNo: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return pocketmoneyAccountHistoryEntityJpaDao.findAllByAccountNoAndCreatedAtBetween(accountNo, fromAt, toAt)
            .map { it.toModel() }
    }
}
