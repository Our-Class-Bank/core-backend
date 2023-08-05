package com.ourclassbank.coreapi.controller.auth.request

import com.ourclassbank.modeldomain.user.RoleType

data class UserSignupRequest(
    val username: String,
    val password: String,
    val name: String,
    val roles: List<RoleType>,
)
