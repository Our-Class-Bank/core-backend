package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.UserRepository
import com.ourclassbank.modeldomain.user.User
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
