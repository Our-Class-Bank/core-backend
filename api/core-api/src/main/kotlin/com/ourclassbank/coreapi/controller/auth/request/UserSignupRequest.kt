package com.ourclassbank.coreapi.controller.auth.request

import com.ourclassbank.modeldomain.user.RoleType

data class UserSignupRequest(
    val username: String,
    val password: String,
    val name: String,
    val pocketmoneyAccountNo: String,
    val roles: List<RoleType>,
    val userClass: UserClassRequest
) {
    data class UserClassRequest(
        val schoolName: String,
        val grade: Int,
        val classNumber: Int,
        val attendanceNumber: Int,
    )
}
