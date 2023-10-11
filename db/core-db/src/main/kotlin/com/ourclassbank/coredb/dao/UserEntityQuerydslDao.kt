package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.QUserEntity.userEntity
import com.ourclassbank.coredb.entity.QUserRoleEntity.userRoleEntity
import com.ourclassbank.coredb.entity.UserEntity
import com.ourclassbank.modeldomain.user.RoleType
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class UserEntityQuerydslDao(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun findAllUserRole(): List<RoleType> {
        return jpaQueryFactory.select(userRoleEntity.role)
            .from(userRoleEntity)
            .distinct()
            .fetch()
    }

    fun findAllByUserClass(
        schoolName: String,
        grade: Int,
        classNumber: Int,
    ): List<UserEntity> {
        return jpaQueryFactory.selectFrom(userEntity)
            .where(
                userEntity.userClass.schoolName.eq(schoolName),
                userEntity.userClass.grade.eq(grade),
                userEntity.userClass.classNumber.eq(classNumber)
            )
            .fetch()
    }
}
