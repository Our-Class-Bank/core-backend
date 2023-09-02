package com.ourclassbank.coredomain.repository

import com.ourclassbank.coredb.dao.PocketmoneyAccountHistoryEntityJpaDao
import com.ourclassbank.coredb.dao.UserEntityJpaDao
import com.ourclassbank.coredb.entity.PocketmoneyAccountHistoryEntity
import com.ourclassbank.coredomain.support.factory.toModel
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistory
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class PocketmoneyAccountHistoryRepository(
    private val pocketmoneyAccountHistoryEntityJpaDao: PocketmoneyAccountHistoryEntityJpaDao,
    private val userEntityJpaDao: UserEntityJpaDao
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
            balance = getBalance(accountNo) + type.toAmountWithSign(amount)
        ).also {
            pocketmoneyAccountHistoryEntityJpaDao.save(it)
        }
    }

    private fun getBalance(accountNo: String): Long {
        return pocketmoneyAccountHistoryEntityJpaDao.findFirstByAccountNoOrderByIdDesc(accountNo)?.balance
            ?: 0L
    }

    fun findAllByAccountNo(
        accountNo: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return pocketmoneyAccountHistoryEntityJpaDao.findAllByAccountNoAndCreatedAtBetweenOrderByCreatedAtDesc(
            accountNo,
            fromAt,
            toAt
        ).map {
            val ownerUserEntity = userEntityJpaDao.findByPocketMoneyAccountNo(it.accountNo) ?: throw IllegalArgumentException("존재하지 않는 회원")
            val createUserEntity = userEntityJpaDao.findByUsername(it.createdBy!!) ?: throw IllegalArgumentException("존재하지 않는 회원")
            it.toModel(ownerUserEntity, createUserEntity)
        }
    }

    fun findAllByCreatedBy(
        createdBy: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistory> {
        return pocketmoneyAccountHistoryEntityJpaDao.findAllByCreatedByAndCreatedAtBetweenOrderByCreatedAtDesc(
            createdBy,
            fromAt,
            toAt
        ).map {
            val ownerUserEntity = userEntityJpaDao.findByPocketMoneyAccountNo(it.accountNo) ?: throw IllegalArgumentException("존재하지 않는 회원")
            val createUserEntity = userEntityJpaDao.findByUsername(it.createdBy!!) ?: throw IllegalArgumentException("존재하지 않는 회원")
            it.toModel(ownerUserEntity, createUserEntity)
        }
    }
}
