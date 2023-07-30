package com.ourclassbank.coredomain.repository

import com.ourclassbank.coredb.dao.CreditScoreHistoryEntityJpaDao
import com.ourclassbank.coredb.entity.CreditScoreHistoryEntity
import com.ourclassbank.coredomain.support.factory.toModel
import com.ourclassbank.modeldomain.user.creditscore.CreditScoreHistory
import com.ourclassbank.modeldomain.user.creditscore.CreditScoreUpdateVo
import org.springframework.stereotype.Repository

@Repository
class CreditScoreRepository(
    private val historyJpaDao: CreditScoreHistoryEntityJpaDao,
) {
    fun update(updateVo: CreditScoreUpdateVo): CreditScoreHistory {
        return updateVo.run {
            val entity = CreditScoreHistoryEntity(
                userLoginId = userLoginId,
                changePoint = changePoint,
                description = description,
                score = getCurrentScore() + changePoint,
            )
            historyJpaDao.save(entity)
        }.toModel()
    }

    private fun CreditScoreUpdateVo.getCurrentScore(): Int {
        return historyJpaDao.findLastByUserLoginIdOrderByIdDesc(userLoginId)?.score ?: 0
    }

    fun findLastHistoryByUserLoginId(userLoginId: String): CreditScoreHistory {
        return historyJpaDao.findLastByUserLoginIdOrderByIdDesc(userLoginId)?.toModel()
            ?: throw IllegalArgumentException("신용평가 이력이 존재하지 않는 회원")
    }
}
