package com.ourclassbank.coredb.entity

import com.ourclassbank.modeldomain.user.RoleType
import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity(name = "user_role")
class UserRoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Comment("권한")
    @Enumerated(EnumType.STRING)
    val role: RoleType,
)
