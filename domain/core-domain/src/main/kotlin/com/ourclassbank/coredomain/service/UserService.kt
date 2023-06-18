package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.UserRepository
import com.ourclassbank.modeldomain.user.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun create(user: User) {
        if (userRepository.existsByLoginId(user.loginId)) throw RuntimeException("이미 존재하는 사용자입니다.")

        userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun findByLoginId(loginId: String): User {
        return userRepository.findByLoginId(loginId)
    }
}
