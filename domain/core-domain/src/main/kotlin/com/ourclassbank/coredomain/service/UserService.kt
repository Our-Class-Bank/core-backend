package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.UserRepository
import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.coredomain.support.exception.DomainExceptionType
import com.ourclassbank.coredomain.support.security.UserContext
import com.ourclassbank.modeldomain.user.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserService(
    private val userRepository: UserRepository,
) {
    private val initPassword = "1234"

    fun create(user: User) {
        if (userRepository.existsByUsername(user.username)) {
            throw DomainException(DomainExceptionType.EXISTS_USER)
        }

        userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun findByUsername(username: String): User {
        return userRepository.findByUser(username)
    }

    @Transactional(readOnly = true)
    fun findAllSameClass(): List<User> {
        (SecurityContextHolder.getContext().authentication.principal as UserContext).uUsername
        val userContext = SecurityContextHolder.getContext().authentication.principal as UserContext
        val userClass = userRepository.findByUser(userContext.uUsername).userClass

        return userRepository.findAllByUserClass(userClass)
    }

    fun passwordReset(username: String, name: String) {
        userRepository.findByUser(username).let {
            if (it.name != name) {
                throw DomainException(DomainExceptionType.INSUFFICIENT_USER_RESET_PASSWORD)
            }

            userRepository.updatePassword(it.copy(password = initPassword))
        }
    }
}
