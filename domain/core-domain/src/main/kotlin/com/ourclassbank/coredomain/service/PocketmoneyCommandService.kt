package com.ourclassbank.coredomain.service

import com.ourclassbank.coredomain.repository.PocketmoneyAccountCommandRepository
import com.ourclassbank.coredomain.service.strategy.PocketmoneyWithdrawValidationStrategy
import com.ourclassbank.coredomain.support.exception.DomainException
import com.ourclassbank.coredomain.support.exception.DomainExceptionType
import com.ourclassbank.coredomain.usecase.PocketmoneyCommandUsecase
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class PocketmoneyCommandService(
    private val commandRepository: PocketmoneyAccountCommandRepository,

    private val pocketmoneyWithdrawValidationStrategy: PocketmoneyWithdrawValidationStrategy,
) : PocketmoneyCommandUsecase {
    override fun deposit(
        accountNo: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    ) {
        commandRepository.save(
            accountNo = accountNo,
            type = type,
            amount = amount,
            description = description
        )
    }

    override fun withdraw(
        accountNo: String,
        type: PocketmoneyAccountHistoryType,
        amount: Long,
        description: String,
    ) {
        commandRepository.findByAccountNo(accountNo)?.let {
            pocketmoneyWithdrawValidationStrategy.validate(amount, it.balance)
        } ?: throw DomainException(DomainExceptionType.INVALID_WITHDRAW_POCKETMONEY_AMOUNT)

        commandRepository.save(
            accountNo = accountNo,
            type = type,
            amount = amount,
            description = description
        )
    }
}
