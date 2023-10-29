package com.ourclassbank.coredomain.usecase

import com.ourclassbank.modeldomain.user.creditevaluation.CreditEvaluationHistory
import java.time.LocalDateTime

interface CreditEvaluationQueryUsecase {
    /**
     * 신용평가 최종 이력을 조회합니다.
     */
    fun findLastHistoryByUser(username: String): CreditEvaluationHistory

    fun findAllHistoryByUser(
        username: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<CreditEvaluationHistory>

    fun findAllHistoryByCreatedBy(
        createdBy: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<CreditEvaluationHistory>

    fun findAllHistoryBySameClass(
        username: String,
        fromAt: LocalDateTime,
        toAt: LocalDateTime
    ): List<CreditEvaluationHistory>

    fun readCurrentScore(username: String): Int
}
