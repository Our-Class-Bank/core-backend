package com.ourclassbank.coredomain.usecase

import com.ourclassbank.modeldomain.user.RoleType

interface AuthUsecase {
    fun signup(username: String, password: String, name: String, roles: List<RoleType>)
    fun signin(username: String, password: String): String
    fun passwordReset(username: String, name: String)
    fun passwordChange(username: String, name: String, newPassword: String)
}
