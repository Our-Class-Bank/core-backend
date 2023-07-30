package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.CreditScoreHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface CreditScoreHistoryEntityJpaDao : JpaRepository<CreditScoreHistoryEntity, Long> {
    fun findFirstByUserLoginIdOrderByIdDesc(userLoginId: String): CreditScoreHistoryEntity?
    fun findAllByUserLoginIdAndCreatedAtBetweenOrderByCreatedAtDesc(
        userLoginId: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<CreditScoreHistoryEntity>
}
