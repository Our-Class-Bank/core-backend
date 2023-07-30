package com.ourclassbank.coredomain.repository

import com.ourclassbank.coredb.dao.UserEntityJpaDao
import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.coredomain.support.exception.DomainExceptionType
import com.ourclassbank.coredomain.support.factory.toEntity
import com.ourclassbank.coredomain.support.factory.toModel
import com.ourclassbank.modeldomain.user.User
import com.ourclassbank.modeldomain.user.UserClass
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

    fun findByUser(username: String): User {
        return jpaDao.findByUsername(username)?.toModel() ?: throw DomainException(DomainExceptionType.NOT_FOUND_USER)
    }

    // todo jpql -> querydsl 로 변경
    fun findAllByUserClass(userClass: UserClass): List<User> {
        return userClass.run {
            jpaDao.findAllByUserClass(
                schoolName = this.schoolName,
                grade = this.grade,
                classNumber = this.classNumber
            ).map { it.toModel() }
        }
    }

    fun existsByUsername(username: String): Boolean {
        return jpaDao.existsByUsername(username)
    }

    fun updatePassword(user: User) {
        jpaDao.findByUsername(user.username)?.updatePassword(passwordEncoder.encode(user.password))
            ?: throw DomainException(DomainExceptionType.NOT_FOUND_USER)
    }
}
