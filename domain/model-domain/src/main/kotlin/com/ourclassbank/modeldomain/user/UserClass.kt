package com.ourclassbank.modeldomain.user

data class UserClass(
    val id: Long? = null,
    val schoolName: String,
    val grade: Int,
    val classNumber: Int,
    val attendanceNumber: Int,
)
