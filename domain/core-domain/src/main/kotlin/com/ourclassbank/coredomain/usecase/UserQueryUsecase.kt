package com.ourclassbank.coredomain.usecase

import com.ourclassbank.modeldomain.user.User

interface UserQueryUsecase {
    fun findByUsername(username: String): User
    fun findAllSameClass(username: String): List<User>
}
