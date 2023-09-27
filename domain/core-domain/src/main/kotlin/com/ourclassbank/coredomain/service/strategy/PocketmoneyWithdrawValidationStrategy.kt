package com.ourclassbank.coredomain.service.strategy

import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.coredomain.support.exception.DomainExceptionType.INVALID_WITHDRAW_POCKETMONEY_AMOUNT
import org.springframework.stereotype.Component

@Component
class PocketmoneyWithdrawValidationStrategy {
    fun validate(amount: Long, balance: Long) {
        `잔액보다 큰 금액은 출금할 수 없습니다`(amount, balance)
    }

    private fun `잔액보다 큰 금액은 출금할 수 없습니다`(amount: Long, balance: Long) {
        if (amount > balance) {
            throw DomainException(INVALID_WITHDRAW_POCKETMONEY_AMOUNT)
        }
    }
}
