package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserEntityJpaDao : JpaRepository<UserEntity, Long> {
    fun findByLoginId(loginId: String): UserEntity?
    fun existsByLoginId(loginId: String): Boolean

    @Query(
        value = """
        select *
from user
where school_name = :schoolName
  and grade = :grade
  and class_number = :classNumber
    """, nativeQuery = true
    )
    fun findAllByUserClass(
        schoolName: String,
        grade: Int,
        classNumber: Int,
    ): List<UserEntity>
}
