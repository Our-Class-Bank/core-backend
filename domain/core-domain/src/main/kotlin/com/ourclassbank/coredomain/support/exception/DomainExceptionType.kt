package com.ourclassbank.coredomain.support.exception

enum class DomainExceptionType(val message: String) {
    // 회원
    INVALID_USER_PASSWORD("비밀번호 불일치"),
    EXISTS_USER("이미 존재하는 회원"),
    NOT_FOUND_USER("존재하지 않는 회원")
    ;
}
