package com.ourclassbank.coredomain.usecase

import com.ourclassbank.modeldomain.user.RoleType
import com.ourclassbank.modeldomain.user.UserClass

interface AdminUsecase {
    fun signup(username: String, password: String, name: String, roles: List<RoleType>, userClass: UserClass)
}
