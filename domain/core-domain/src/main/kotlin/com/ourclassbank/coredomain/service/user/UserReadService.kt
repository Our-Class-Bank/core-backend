package com.ourclassbank.coredomain.service.user

import com.ourclassbank.coredomain.repository.UserRepository
import com.ourclassbank.coredomain.support.security.UserContext
import com.ourclassbank.modeldomain.user.User
import org.springframework.security.core.context.SecurityContextHolder
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

    fun findAllSameClass(): List<User> {
        (SecurityContextHolder.getContext().authentication.principal as UserContext).uUsername
        val userContext = SecurityContextHolder.getContext().authentication.principal as UserContext
        val userClass = userRepository.findByUser(userContext.uUsername).userClass

        return userRepository.findAllByUserClass(userClass)
    }
}
