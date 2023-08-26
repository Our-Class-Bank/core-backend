package com.ourclassbank.coredomain.usecase

interface AuthUsecase {
    fun signin(username: String, password: String): String
    fun passwordReset(username: String, name: String)
    fun passwordChange(username: String, name: String, newPassword: String)
}
