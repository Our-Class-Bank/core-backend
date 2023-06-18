package com.ourclassbank.coredomain.support.factory

import com.ourclassbank.coredb.entity.UserEntity
import com.ourclassbank.modeldomain.user.User

fun User.toEntity(): UserEntity {
    return UserEntity(
        loginId = this.loginId,
        password = this.password,
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
