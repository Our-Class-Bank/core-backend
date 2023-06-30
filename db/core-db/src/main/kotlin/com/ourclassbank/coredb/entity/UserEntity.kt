package com.ourclassbank.coredb.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity(name = "user")
class UserEntity(
    @Comment("로그인ID")
    @Column(unique = true)
    val loginId: String,

    @Comment("비밀번호")
    var password: String,
    @Comment("이름")
    val name: String,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "user_id")
    val roles: List<UserRoleEntity>,
) : BaseEntity() {
    fun updatePassword(password: String) {
        this.password = password
    }
}
