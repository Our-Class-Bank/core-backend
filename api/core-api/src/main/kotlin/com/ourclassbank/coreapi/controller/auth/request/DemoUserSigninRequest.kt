package com.ourclassbank.coreapi.controller.auth.request

data class DemoUserSigninRequest(
    val demoUserType: DemoUserType,
) {
    enum class DemoUserType(val username: String) {
        TEACHER("demo001"),
        BANKER("demo002"),
        CREDIT_EVALUATOR("demo003"),
        STUDENT1("demo004"),
        STUDENT2("demo005"),
        STUDENT3("demo006")
    }
}
