package com.ourclassbank.coreapi.controller.common

import com.ourclassbank.modeldomain.user.UserClass

data class UserClassResponse(
    val schoolName: String,
    val grade: Int,
    val classNumber: Int,
    val attendanceNumber: Int,
) {
    constructor(userClass: UserClass) : this(
        schoolName = userClass.schoolName,
        grade = userClass.grade,
        classNumber = userClass.classNumber,
        attendanceNumber = userClass.attendanceNumber,
    )
}
