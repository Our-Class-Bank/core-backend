package com.ourclassbank.coredb.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedBy

@Table(name = "credit_score_history")
@Entity
class CreditScoreHistoryEntity(
    val userLoginId: String,

    @Comment("점수 변동")
    val changePoint: Int,
    @Comment("상세 내용")
    val description: String,

    @Comment("변동 후 점수")
    val score: Int,
) : BaseEntity() {
    @CreatedBy
    @Column(updatable = false)
    var createdBy: String? = null
        private set
}
