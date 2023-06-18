package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserEntityJpaDao : JpaRepository<UserEntity, Long> {
    fun findByLoginId(loginId: String): UserEntity?
}
