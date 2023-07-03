package com.ourclassbank.coredb.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Embeddable
class UserClassEntity(
    @Comment("학교명")
    val schoolName: String,

    @Comment("학년")
    val grade: Int,

    @Comment("반")
    val classNumber: Int,

    @Comment("출석번호")
    val attendanceNumber: Int,
)
