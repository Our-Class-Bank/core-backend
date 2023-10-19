package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.CreditEvaluationRepository
import com.ourclassbank.coredomain.repository.PocketmoneyAccountQueryRepository
import com.ourclassbank.coredomain.repository.UserRepository
import com.ourclassbank.coredomain.usecase.DashboardQueryUsecase
import com.ourclassbank.modeldomain.user.RoleType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class DashboardQueryService(
    private val userRepository: UserRepository,
    private val pocketmoneyAccountQueryRepository: PocketmoneyAccountQueryRepository,
    private val creditEvaluationRepository: CreditEvaluationRepository
) : DashboardQueryUsecase {
    override fun userCount(): Long {
        return userRepository.count()
    }

    override fun userRole(): List<RoleType> {
        return userRepository.findAllUserRole()
    }

    override fun accountHistoryCount(): Long {
        return pocketmoneyAccountQueryRepository.count()
    }

    override fun creditEvaluationCount(): Long {
        return creditEvaluationRepository.count()
    }
}
