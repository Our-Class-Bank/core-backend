package com.ourclassbank.coreapi.support.monitor

import com.ourclassbank.coreapi.support.monitor.request.SlackSendMessageRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class ErrorMonitorComponent(
    @Value("\${slack.error-webhook-key}") private val webhookKey: String,
    private val slackFeignAdapter: SlackFeignAdapter
) {
    fun slackSendMessage(request: SlackSendMessageRequest) {
        slackFeignAdapter.sendMessage(webhookKey, request)
    }
}
