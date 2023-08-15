package com.ourclassbank.coredomain.usecase

import com.ourclassbank.modeldomain.user.User

interface UserCommandUsecase {
    fun create(user: User)
    fun passwordReset(username: String, name: String)
    fun passwordChange(username: String, newPassword: String)

    /**
     * 비밀번호 변경 가능 조건
     * - 로그인ID 와 이름이 저장된 정보와 일치해야 합니다.
     */
    fun passwordChangeAble(username: String, name: String): Boolean
}
