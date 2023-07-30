package com.ourclassbank.modeldomain.user

data class User(
    val id: Long? = null,
    val username: String,
    val password: String,

    val name: String,
    val pocketmoneyAccountNo: String,

    val roles: List<RoleType>,
    val userClass: UserClass
)
