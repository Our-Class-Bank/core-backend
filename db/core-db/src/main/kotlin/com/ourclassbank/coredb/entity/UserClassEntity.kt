package com.ourclassbank.coredb.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity(name = "user_class")
class UserClassEntity(
    @Id
    @Column(name = "user_class_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Comment("학교명")
    val schoolName: String,

    @Comment("학년")
    val grade: Int,

    @Comment("반")
    val classNumber: Int,

    @Comment("출석번호")
    val attendanceNumber: Int,
)
