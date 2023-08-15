package com.ourclassbank.coreapi.controller.sameclass

import com.ourclassbank.coreapi.controller.common.UserResponse
import com.ourclassbank.coreapi.controller.sameclass.response.SameClassCreditEvaluationResponse
import com.ourclassbank.coredomain.service.creditevaluation.CreditEvaluationReadService
import com.ourclassbank.coredomain.support.security.UserContext
import com.ourclassbank.coredomain.usecase.UserQueryUsecase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "같은 반", description = "auth: STUDENT")
@RestController
class SameClassController(
    private val userQueryUsecase: UserQueryUsecase,
    private val creditEvaluationReadService: CreditEvaluationReadService
) {
    @Operation(summary = "회원 전체 조회")
    @GetMapping("/api/v1/same-class/user")
    fun findAllUser(): List<UserResponse> {
        val userContext = getUserContext()
        return userQueryUsecase.findAllSameClass(userContext.uUsername)
            .map { UserResponse(it) }
    }

    @Operation(summary = "현재 신용평가 점수 조회")
    @GetMapping("/api/v1/same-class/credit-evaluation")
    fun findAllCreditEvaluation(): List<SameClassCreditEvaluationResponse> {
        val userContext = getUserContext()
        val findAllSameClass = userQueryUsecase.findAllSameClass(userContext.uUsername)

        return findAllSameClass.map {
            SameClassCreditEvaluationResponse(
                attendanceNumber = it.userClass.attendanceNumber,
                studentName = it.name,
                score = creditEvaluationReadService.readCurrentScore(it.username)
            )
        }
    }

    private fun getUserContext(): UserContext {
        return SecurityContextHolder.getContext().authentication.principal as UserContext
    }
}
