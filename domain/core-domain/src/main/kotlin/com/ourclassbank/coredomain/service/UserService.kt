package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.UserRepository
import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.coredomain.support.exception.DomainExceptionType
import com.ourclassbank.modeldomain.user.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserService(
    private val userRepository: UserRepository,
) {
    private val initPassword = "1234"

    fun create(user: User) {
        if (userRepository.existsByLoginId(user.loginId)) {
            throw DomainException(DomainExceptionType.EXISTS_USER)
        }

        userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun findByLoginId(loginId: String): User {
        return userRepository.findByLoginId(loginId)
    }

    fun passwordReset(loginId: String, name: String) {
        userRepository.findByLoginId(loginId).let {
            if (it.name != name) {
                throw DomainException(DomainExceptionType.INSUFFICIENT_USER_RESET_PASSWORD)
            }

            userRepository.updatePassword(it.copy(password = initPassword))
        }
    }
}
