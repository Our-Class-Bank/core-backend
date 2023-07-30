package com.ourclassbank.coredomain.repository

import com.ourclassbank.coredb.dao.CreditEvaluationHistoryEntityJpaDao
import com.ourclassbank.coredb.entity.CreditEvaluationHistoryEntity
import com.ourclassbank.coredomain.support.factory.toModel
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluateVo
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluationHistory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class CreditEvaluationRepository(
    private val historyJpaDao: CreditEvaluationHistoryEntityJpaDao,
) {
    fun evaluate(updateVo: CreditEvaluateVo): CreditEvaluationHistory {
        return updateVo.run {
            val entity = CreditEvaluationHistoryEntity(
                username = username,
                changePoint = changePoint,
                description = description,
                score = getCurrentScore() + changePoint,
            )
            historyJpaDao.save(entity)
        }.toModel()
    }

    private fun CreditEvaluateVo.getCurrentScore(): Int {
        return historyJpaDao.findFirstByUsernameOrderByIdDesc(username)?.score ?: 0
    }

    fun findLastHistoryByUser(username: String): CreditEvaluationHistory {
        return historyJpaDao.findFirstByUsernameOrderByIdDesc(username)?.toModel()
            ?: throw IllegalArgumentException("신용평가 이력이 존재하지 않는 회원")
    }

    fun findAllHistoryByUser(username: String, fromAt: LocalDateTime, toAt: LocalDateTime): List<CreditEvaluationHistory> {
        return historyJpaDao.findAllByUsernameAndCreatedAtBetweenOrderByCreatedAtDesc(
            username,
            fromAt,
            toAt
        ).map { it.toModel() }
    }
}
