package com.ourclassbank.coredomain.repository

import com.ourclassbank.coredb.dao.UserEntityJpaDao
import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.coredomain.support.exception.DomainExceptionType
import com.ourclassbank.coredomain.support.factory.toEntity
import com.ourclassbank.coredomain.support.factory.toModel
import com.ourclassbank.modeldomain.user.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val passwordEncoder: PasswordEncoder,

    private val jpaDao: UserEntityJpaDao,
) {
    fun save(user: User) {
        jpaDao.save(user.toEntity(passwordEncoder))
    }

    fun findByLoginId(loginId: String): User {
        return jpaDao.findByLoginId(loginId)?.toModel() ?: throw DomainException(DomainExceptionType.NOT_FOUND_USER)
    }

    fun existsByLoginId(loginId: String): Boolean {
        return jpaDao.existsByLoginId(loginId)
    }
}
