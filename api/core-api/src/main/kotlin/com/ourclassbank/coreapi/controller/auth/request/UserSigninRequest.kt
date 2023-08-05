package com.ourclassbank.coreapi.controller.auth.request

data class UserSigninRequest(
    val username: String,
    val password: String,
)
