package com.ourclassbank.coredomain.service.user

import com.ourclassbank.coredomain.repository.UserRepository
import com.ourclassbank.modeldomain.user.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class UserReadService(
    private val userRepository: UserRepository,
) {
    fun findByUsername(username: String): User {
        return userRepository.findByUser(username)
    }

    fun findAllSameClass(username: String): List<User> {
        val userClass = userRepository.findByUser(username).userClass
        return userRepository.findAllByUserClass(userClass)
    }
}
