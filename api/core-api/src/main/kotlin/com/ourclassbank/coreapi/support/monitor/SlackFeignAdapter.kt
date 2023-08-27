package com.ourclassbank.coreapi.support.monitor

import com.ourclassbank.coreapi.support.monitor.request.SlackSendMessageRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "slack", url = "https://hooks.slack.com/services")
interface SlackFeignAdapter {
    @PostMapping("/{key}")
    fun sendMessage(@PathVariable key: String, @RequestBody request: SlackSendMessageRequest)
}
