package com.ourclassbank.coredomain.support.generator

import java.util.*

object PocketMoneyAccountNoGenerator {
    fun take(): String {
        return UUID.randomUUID().toString()
    }
}
