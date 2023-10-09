package com.ourclassbank.coredomain.repository

import com.ourclassbank.coredb.dao.PocketmoneyAccountHistoryEntityJpaDao
import com.ourclassbank.coredb.dao.PocketmoneyAccountHistoryEntityQuerydslDao
import com.ourclassbank.coredb.entity.PocketmoneyAccountHistoryEntity
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import org.springframework.stereotype.Repository

@Repository
class PocketmoneyAccountCommandRepository(
    private val pocketmoneyAccountHistoryEntityJpaDao: PocketmoneyAccountHistoryEntityJpaDao,
    private val pocketmoneyAccountHistoryEntityQuerydslDao: PocketmoneyAccountHistoryEntityQuerydslDao
) {
    fun findByAccountNo(accountNo: String): PocketmoneyAccountHistoryEntity? {
        return pocketmoneyAccountHistoryEntityQuerydslDao.findFirstByAccountNoOrderByIdDescWithLock(accountNo)
    }

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
            balance = getBalance(accountNo) + type.toAmountWithSign(amount)
        ).also {
            pocketmoneyAccountHistoryEntityJpaDao.save(it)
        }
    }

    private fun getBalance(accountNo: String): Long {
        return pocketmoneyAccountHistoryEntityJpaDao.findFirstByAccountNoOrderByIdDesc(accountNo)?.balance
            ?: 0L
    }
}
