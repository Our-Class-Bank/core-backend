package com.ourclassbank.coreapi.controller.auth.response

data class UserPasswordChangeRequest(
    val username: String,
    val name: String,
    val newPassword: String
)
