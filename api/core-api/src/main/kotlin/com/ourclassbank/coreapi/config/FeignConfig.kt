package com.ourclassbank.coreapi.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = ["com.ourclassbank.coreapi.support.monitor"])
class FeignConfig
