package com.ourclassbank.coredomain.model

data class User(
    val loginId: String,
    val password: String,
    val name: String,
    val roles: List<RoleType>,
)
