package com.ourclassbank.coredomain.support.exception

enum class DomainExceptionType(val message: String) {
    // 회원
    INVALID_USER_PASSWORD("비밀번호 불일치"),
    EXISTS_USER("이미 존재하는 회원"),
    NOT_FOUND_USER("존재하지 않는 회원"),
    INSUFFICIENT_USER_PASSWORD_RESET("회원 비밀번호 초기화 조건 불충족"),
    INSUFFICIENT_USER_PASSWORD_CHANGE("회원 비밀번호 변경 조건 불충족"),

    // 신용평가
    INVALID_CREDIT_EVALUATION_SCORE("잘못된 신용평가 점수"),
    ;
}
