package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.CreditEvaluationHistoryEntity
import com.ourclassbank.coredb.entity.QCreditEvaluationHistoryEntity.creditEvaluationHistoryEntity
import com.ourclassbank.coredb.entity.QUserEntity
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class CreditEvaluationHistoryEntityQuerydslDao(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun findAllHistoryBySameClass(
        username: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<CreditEvaluationHistoryEntity> {
        println("###")
        val uTeacher = QUserEntity("u_teacher")
        val uStudent = QUserEntity("u_student")

        return jpaQueryFactory.selectFrom(creditEvaluationHistoryEntity)
            .where(
                creditEvaluationHistoryEntity.username.`in`(
                    JPAExpressions
                        .select(uStudent.username)
                        .from(uTeacher)
                        .join(uStudent)
                        .on(
                            uTeacher.userClass.schoolName.eq(uStudent.userClass.schoolName)
                                .and(uTeacher.userClass.grade.eq(uStudent.userClass.grade))
                                .and(uTeacher.userClass.classNumber.eq(uStudent.userClass.classNumber))
                        )
                        .where(uTeacher.username.eq(username))
                ).and(creditEvaluationHistoryEntity.createdAt.between(fromAt, toAt))
            ).fetch()
    }
}
