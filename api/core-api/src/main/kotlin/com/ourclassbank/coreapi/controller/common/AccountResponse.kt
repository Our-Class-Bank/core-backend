package com.ourclassbank.coreapi.controller.common

import com.ourclassbank.modeldomain.common.AccountType

data class AccountResponse(
    val type: AccountType,
    val number: String,
)
