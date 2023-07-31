package com.ourclassbank.coreapi.controller.common

import com.ourclassbank.modeldomain.user.User

data class UserResponse(
    val username: String,
    val name: String,
    val pocketmoneyAccountNo: String,
) {
    constructor(user: User) : this(
        user.username,
        user.name,
        user.pocketmoneyAccountNo
    )
}
