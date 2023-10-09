package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserEntityJpaDao : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
    fun existsByUsername(username: String): Boolean
    fun findByPocketMoneyAccountNo(pocketMoneyAccountNo: String): UserEntity?
}
