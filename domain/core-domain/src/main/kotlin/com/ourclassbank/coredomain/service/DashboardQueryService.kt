package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.UserRepository
import com.ourclassbank.coredomain.usecase.DashboardQueryUsecase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class DashboardQueryService(
    private val userRepository: UserRepository,
) : DashboardQueryUsecase {
    override fun readAllUserCount(): Int {
        return userRepository.findAllUserCount()
    }
}
