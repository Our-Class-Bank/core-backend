package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.CreditEvaluationHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface CreditEvaluationHistoryEntityJpaDao : JpaRepository<CreditEvaluationHistoryEntity, Long> {
    fun findFirstByUsernameOrderByIdDesc(username: String): CreditEvaluationHistoryEntity?

    fun findAllByUsernameAndCreatedAtBetweenOrderByCreatedAtDesc(
        username: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<CreditEvaluationHistoryEntity>

    fun findAllByCreatedByAndCreatedAtBetweenOrderByCreatedAtDesc(
        createdBy: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<CreditEvaluationHistoryEntity>
}
