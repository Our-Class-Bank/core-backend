package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.model.RoleType
import com.ourclassbank.coredomain.model.User
import com.ourclassbank.coredomain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun signup(loginId: String, password: String, name: String, roles: List<RoleType>) {
        userRepository.save(User(loginId, password, name, roles))
    }

    fun findByLoginId(loginId: String): User {
        return userRepository.findByLoginId(loginId)
    }
}
