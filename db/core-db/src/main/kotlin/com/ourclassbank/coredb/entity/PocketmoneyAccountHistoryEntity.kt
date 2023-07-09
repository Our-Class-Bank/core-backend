package com.ourclassbank.coredb.entity

import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity(name = "pocketmoney_account_history")
class PocketmoneyAccountHistoryEntity(
    @Enumerated(EnumType.STRING)
    @Comment("용돈계좌기록 구분")
    val type: PocketmoneyAccountHistoryType,

    @Comment("입출금 금액")
    val amount: Long,
    @Comment("상세 내용")
    val description: String,
    @Comment("현재 잔액")
    val balance: Long,

    @Comment("용돈계좌 소유자 loginId")
    val loginId: String,
) : BaseEntity()
