package com.ourclassbank.coredb.dao

import com.ourclassbank.coredb.entity.PocketmoneyAccountHistoryEntity
import com.ourclassbank.coredb.entity.QPocketmoneyAccountHistoryEntity.pocketmoneyAccountHistoryEntity
import com.ourclassbank.coredb.entity.QUserEntity
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.LockModeType
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class PocketmoneyAccountHistoryEntityQuerydslDao(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun findFirstByAccountNoOrderByIdDescWithLock(accountNo: String): PocketmoneyAccountHistoryEntity? {
        return jpaQueryFactory.selectFrom(pocketmoneyAccountHistoryEntity)
            .where(pocketmoneyAccountHistoryEntity.accountNo.eq(accountNo))
            .orderBy(pocketmoneyAccountHistoryEntity.id.desc())
            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
            .fetchFirst()
    }

    fun findAllHistoryBySameClass(
        username: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<PocketmoneyAccountHistoryEntity> {
        val uTeacher = QUserEntity("u_teacher")
        val uStudent = QUserEntity("u_student")

        return jpaQueryFactory.selectFrom(pocketmoneyAccountHistoryEntity)
            .where(
                pocketmoneyAccountHistoryEntity.accountNo.`in`(
                    JPAExpressions
                        .select(uStudent.pocketMoneyAccountNo)
                        .from(uTeacher)
                        .join(uStudent)
                        .on(
                            uTeacher.userClass.schoolName.eq(uStudent.userClass.schoolName)
                                .and(uTeacher.userClass.grade.eq(uStudent.userClass.grade))
                                .and(uTeacher.userClass.classNumber.eq(uStudent.userClass.classNumber))
                        )
                        .where(uTeacher.username.eq(username))
                ).and(pocketmoneyAccountHistoryEntity.createdAt.between(fromAt, toAt))
            ).fetch()
    }
}
