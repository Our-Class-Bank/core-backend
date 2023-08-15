package com.ourclassbank.coredomain.service.user

import com.ourclassbank.coredomain.repository.UserRepository
import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.coredomain.support.exception.DomainExceptionType.EXISTS_USER
import com.ourclassbank.coredomain.support.exception.DomainExceptionType.INSUFFICIENT_USER_PASSWORD_RESET
import com.ourclassbank.coredomain.usecase.UserCommandUsecase
import com.ourclassbank.modeldomain.user.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserCommandService(
    private val userRepository: UserRepository,
) : UserCommandUsecase {
    private val initPassword = "1234"

    override fun create(user: User) {
        if (userRepository.existsByUsername(user.username)) {
            throw DomainException(EXISTS_USER)
        }

        userRepository.save(user)
    }

    override fun passwordReset(username: String, name: String) {
        userRepository.findByUser(username).let {
            if (it.name != name) {
                throw DomainException(INSUFFICIENT_USER_PASSWORD_RESET)
            }

            userRepository.updatePassword(username, initPassword)
        }
    }

    override fun passwordChange(username: String, newPassword: String) {
        userRepository.updatePassword(username, newPassword)
    }

    /**
     * 비밀번호 변경 가능 조건
     * - 로그인ID 와 이름이 저장된 정보와 일치해야 합니다.
     */
    override fun passwordChangeAble(username: String, name: String): Boolean {
        return userRepository.findByUser(username).name == name
    }
}
