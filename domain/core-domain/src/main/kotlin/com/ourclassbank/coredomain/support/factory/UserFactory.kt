package com.ourclassbank.coredomain.support.factory

import com.ourclassbank.coredb.entity.UserEntity
import com.ourclassbank.modeldomain.user.User
import org.springframework.security.crypto.password.PasswordEncoder

fun User.toEntity(passwordEncoder: PasswordEncoder): UserEntity {
    return UserEntity(
        loginId = this.loginId,
        password = passwordEncoder.encode(this.password),
        name = this.name,
        role = this.roles[0],
    )
}

fun UserEntity.toModel(): User {
    return User(
        loginId = this.loginId,
        password = this.password,
        name = this.name,
        roles = listOf(this.role),
    )
}
