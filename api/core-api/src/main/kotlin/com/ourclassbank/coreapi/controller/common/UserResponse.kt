package com.ourclassbank.coreapi.controller.common

import com.ourclassbank.modeldomain.user.User

data class UserResponse(
    val username: String,
    val name: String,
    val pocketmoneyAccountNo: String,
    val userClass: UserClassResponse
) {
    constructor(user: User) : this(
        username = user.username,
        name = user.name,
        pocketmoneyAccountNo = user.pocketmoneyAccountNo,
        userClass = UserClassResponse(user.userClass)
    )
}
