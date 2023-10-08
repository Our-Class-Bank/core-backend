package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.QUserEntity.userEntity
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
}
