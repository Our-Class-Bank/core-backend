package com.ourclassbank.coredb.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity(name = "user")
class UserEntity(
    @Comment("로그인ID")
    @Column(unique = true)
    val loginId: String,

    @Comment("비밀번호")
    val password: String,
    @Comment("이름")
    val name: String,
    @Comment("권한")
    val role: String,
) : BaseEntity()
