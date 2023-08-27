package com.ourclassbank.coreapi

import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.FeignAutoConfiguration

@ImportAutoConfiguration(
    value = [FeignAutoConfiguration::class]
)
@SpringBootApplication(
    scanBasePackages = [
        "com.ourclassbank.coreapi",
        "com.ourclassbank.coredomain",
    ]
)
class CoreApiApplication

fun main(args: Array<String>) {
    runApplication<CoreApiApplication>(*args)
}
