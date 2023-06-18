package com.ourclassbank.coredomain.repository

import com.ourclassbank.coredb.dao.UserEntityJpaDao
import com.ourclassbank.coredomain.support.factory.toEntity
import com.ourclassbank.coredomain.support.factory.toModel
import com.ourclassbank.modeldomain.user.User
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val jpaDao: UserEntityJpaDao,
) {
    fun save(user: User) {
        jpaDao.save(user.toEntity())
    }

    fun findByLoginId(loginId: String): User {
        return jpaDao.findByLoginId(loginId)?.toModel() ?: throw RuntimeException("존재하지 않는 사용자입니다.")
    }
}
