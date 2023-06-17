package com.ourclassbank.coredomain.repository

import com.ourclassbank.coredomain.model.User
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    private val map: HashMap<String, User> = hashMapOf()

    fun save(user: User) {
        map[user.loginId] = user
    }

    fun findByLoginId(findByLoginId: String): User {
        return map[findByLoginId] ?: throw RuntimeException("User not found")
    }
}
