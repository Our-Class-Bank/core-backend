package com.ourclassbank.coredomain

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.ourclassbank.coredomain",
        "com.ourclassbank.coredb",
    ]
)

class CoreDomainApplication

fun main(args: Array<String>) {
    runApplication<CoreDomainApplication>(*args)
}
