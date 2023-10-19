package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.PocketmoneyAccountHistoryEntity
import com.ourclassbank.coredb.entity.QPocketmoneyAccountHistoryEntity.pocketmoneyAccountHistoryEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.LockModeType
import org.springframework.stereotype.Repository

@Repository
class PocketmoneyAccountHistoryEntityQuerydslDao(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun findFirstByAccountNoOrderByIdDescWithLock(accountNo: String): PocketmoneyAccountHistoryEntity? {
        return jpaQueryFactory.selectFrom(pocketmoneyAccountHistoryEntity)
            .where(pocketmoneyAccountHistoryEntity.accountNo.eq(accountNo))
            .orderBy(pocketmoneyAccountHistoryEntity.id.desc())
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetchFirst()
    }
}
