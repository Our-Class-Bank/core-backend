package com.ourclassbank.coredomain.usecase

import com.ourclassbank.modeldomain.user.RoleType

interface DashboardQueryUsecase {
    fun userCount(): Long
    fun userRole(): List<RoleType>
    fun accountHistoryCount(): Long
    fun creditEvaluationCount(): Long
}
