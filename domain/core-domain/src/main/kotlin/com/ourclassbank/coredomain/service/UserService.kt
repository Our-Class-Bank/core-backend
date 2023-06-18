package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.model.User
import com.ourclassbank.coredomain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun save(user: User) {
        userRepository.save(user)
    }

    fun findByLoginId(loginId: String): User {
        return userRepository.findByLoginId(loginId)
    }
}
