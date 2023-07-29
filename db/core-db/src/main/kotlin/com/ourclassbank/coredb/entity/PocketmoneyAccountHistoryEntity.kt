package com.ourclassbank.coredb.entity

import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedBy

@Table(name = "pocketmoney_account_history")
@Entity
class PocketmoneyAccountHistoryEntity(
    @Comment("용돈계좌번호")
    val accountNo: String,

    @Enumerated(EnumType.STRING)
    @Comment("용돈계좌기록 구분")
    val type: PocketmoneyAccountHistoryType,

    @Comment("입출금 금액")
    val amount: Long,
    @Comment("상세 내용")
    val description: String,
    @Comment("현재 잔액")
    val balance: Long,
) : BaseEntity() {
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    var createdBy: String? = null
        private set
}
