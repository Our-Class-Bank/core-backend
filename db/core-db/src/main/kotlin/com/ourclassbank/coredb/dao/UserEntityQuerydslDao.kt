package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.QUserEntity.userEntity
import com.ourclassbank.coredb.entity.UserEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class UserEntityQuerydslDao(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun findAllUserCount(): Int {
        return jpaQueryFactory.select(userEntity.count())
            .from(userEntity)
            .where(userEntity.userClass.schoolName.ne("우리초등학교"))
            .fetch().count()
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
