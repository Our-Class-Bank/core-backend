package com.ourclassbank.coredomain.support.factory

import com.ourclassbank.coredb.entity.UserEntity
import com.ourclassbank.coredb.entity.UserRoleEntity
import com.ourclassbank.modeldomain.user.RoleType
import com.ourclassbank.modeldomain.user.User
import org.springframework.security.crypto.password.PasswordEncoder

fun User.toEntity(passwordEncoder: PasswordEncoder): UserEntity {
    return UserEntity(
        loginId = this.loginId,
        password = passwordEncoder.encode(this.password),
        name = this.name,
        roles = this.roles.map { it.toEntity() },
    )
}

fun UserEntity.toModel(): User {
    return User(
        loginId = this.loginId,
        password = this.password,
        name = this.name,
        roles = this.roles.map { it.toModel() }
    )
}

fun RoleType.toEntity(): UserRoleEntity {
    return UserRoleEntity(
        role = this
    )
}

fun UserRoleEntity.toModel(): RoleType {
    return this.role
}
