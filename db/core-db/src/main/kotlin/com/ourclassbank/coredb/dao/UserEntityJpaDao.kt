package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserEntityJpaDao : JpaRepository<UserEntity, Long> {
    fun findByUsername(username: String): UserEntity?
    fun existsByUsername(username: String): Boolean

    @Query(
        """
            select u
            from UserEntity u
            where u.userClass.schoolName = :schoolName
              and u.userClass.grade = :grade
              and u.userClass.classNumber = :classNumber
        """
    )
    fun findAllByUserClass(
        schoolName: String,
        grade: Int,
        classNumber: Int,
    ): List<UserEntity>
}
