package com.ourclassbank.coredb.entity

import com.ourclassbank.modeldomain.user.RoleType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.Comment

@Table(name = "user_role")
@Entity
class UserRoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Comment("권한")
    @Enumerated(EnumType.STRING)
    val role: RoleType,
)
