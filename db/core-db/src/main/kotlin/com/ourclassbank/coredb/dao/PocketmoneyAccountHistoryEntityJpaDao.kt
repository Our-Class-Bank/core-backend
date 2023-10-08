package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.PocketmoneyAccountHistoryEntity
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface PocketmoneyAccountHistoryEntityJpaDao : JpaRepository<PocketmoneyAccountHistoryEntity, Long> {
    fun findAllByAccountNoAndCreatedAtBetweenOrderByCreatedAtDesc(
        accountNo: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistoryEntity>

    fun findAllByCreatedByAndCreatedAtBetweenOrderByCreatedAtDesc(
        createdBy: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistoryEntity>

    fun findAllByCreatedAtBetweenOrderByCreatedAtDesc(
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistoryEntity>

    fun findFirstByAccountNoOrderByIdDesc(accountNo: String): PocketmoneyAccountHistoryEntity?

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(
        """
            select p
            from PocketmoneyAccountHistoryEntity p
            where p.accountNo = :accountNo
            order by p.id desc
            limit 1
    """
    )
    fun findFirstByAccountNoOrderByIdDescWithLock(accountNo: String): PocketmoneyAccountHistoryEntity?
}
