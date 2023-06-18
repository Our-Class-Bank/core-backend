package com.ourclassbank.coredb.entity

import com.ourclassbank.modeldomain.user.RoleType
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
    @Enumerated(EnumType.STRING)
    val role: RoleType,
) : BaseEntity()
