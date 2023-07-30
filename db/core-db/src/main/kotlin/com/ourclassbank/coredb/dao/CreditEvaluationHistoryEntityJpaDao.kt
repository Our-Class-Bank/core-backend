package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.CreditEvaluationHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface CreditEvaluationHistoryEntityJpaDao : JpaRepository<CreditEvaluationHistoryEntity, Long> {
    fun findFirstByUserLoginIdOrderByIdDesc(userLoginId: String): CreditEvaluationHistoryEntity?
    fun findAllByUserLoginIdAndCreatedAtBetweenOrderByCreatedAtDesc(
        userLoginId: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<CreditEvaluationHistoryEntity>
}
