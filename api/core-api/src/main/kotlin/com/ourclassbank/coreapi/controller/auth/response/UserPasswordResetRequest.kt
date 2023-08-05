package com.ourclassbank.coreapi.controller.auth.response

data class UserPasswordResetRequest(
    val username: String,
    val name: String,
)
