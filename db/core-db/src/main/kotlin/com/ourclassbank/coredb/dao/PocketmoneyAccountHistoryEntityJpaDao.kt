package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.PocketmoneyAccountHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface PocketmoneyAccountHistoryEntityJpaDao : JpaRepository<PocketmoneyAccountHistoryEntity, Long> {
    fun findAllByLoginIdAndCreatedAtBetween(
        loginId: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistoryEntity>
}