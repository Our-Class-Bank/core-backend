package com.ourclassbank.coredomain.repository

import com.ourclassbank.coredb.dao.CreditEvaluationHistoryEntityJpaDao
import com.ourclassbank.coredb.entity.CreditEvaluationHistoryEntity
import com.ourclassbank.coredomain.repository.strategy.CreditEvaluateRepositoryValidationStrategy
import com.ourclassbank.coredomain.support.factory.toModel
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluateVo
import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluationHistory
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class CreditEvaluationRepository(
    private val evaluateRepositoryValidationStrategy: CreditEvaluateRepositoryValidationStrategy,

    private val historyJpaDao: CreditEvaluationHistoryEntityJpaDao,
) {
    fun evaluate(evaluateVo: CreditEvaluateVo): CreditEvaluationHistory {
        return evaluateVo.run {
            val evaluateScore = getEvaluateScore(username, changePoint)

            evaluateRepositoryValidationStrategy.validate(evaluateVo, evaluateScore)

            CreditEvaluationHistoryEntity(
                username = username,
                changePoint = changePoint,
                description = description,
                score = evaluateScore
            ).run {
                historyJpaDao.save(this)
            }
        }.toModel()
    }

    /**
     * 최초 신용평가시 점수는 0 에서 시작합니다.
     */
    private fun getEvaluateScore(username: String, changePoint: Int): Int {
        return historyJpaDao.findFirstByUsernameOrderByIdDesc(username)?.let {
            it.score + changePoint
        } ?: changePoint
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
