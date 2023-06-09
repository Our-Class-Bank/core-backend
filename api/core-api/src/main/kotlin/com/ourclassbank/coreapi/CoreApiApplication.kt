package com.ourclassbank.coreapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.ourclassbank.coreapi",
        "com.ourclassbank.coredomain",
        "com.ourclassbank.coredb",
    ]
)
class CoreApiApplication

fun main(args: Array<String>) {
    runApplication<CoreApiApplication>(*args)
}
