package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.PocketmoneyAccountHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository
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
}
