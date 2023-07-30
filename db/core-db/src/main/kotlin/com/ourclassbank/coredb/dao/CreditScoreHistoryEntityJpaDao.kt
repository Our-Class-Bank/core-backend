package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.CreditScoreHistoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CreditScoreHistoryEntityJpaDao : JpaRepository<CreditScoreHistoryEntity, Long> {
    fun findLastByUserLoginIdOrderByIdDesc(userLoginId: String): CreditScoreHistoryEntity?
}
