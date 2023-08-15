package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.UserRepository
import com.ourclassbank.coredomain.usecase.UserQueryUsecase
import com.ourclassbank.modeldomain.user.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class UserQueryService(
    private val userRepository: UserRepository,
) : UserQueryUsecase {
    override fun findByUsername(username: String): User {
        return userRepository.findByUser(username)
    }

    override fun findAllSameClass(username: String): List<User> {
        val userClass = userRepository.findByUser(username).userClass
        return userRepository.findAllByUserClass(userClass)
    }
}
