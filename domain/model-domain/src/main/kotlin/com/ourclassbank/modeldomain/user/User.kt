package com.ourclassbank.modeldomain.user

data class User(
    val id: Long? = null,
    val loginId: String,
    val password: String,
    val name: String,
    val roles: List<RoleType>,
)
