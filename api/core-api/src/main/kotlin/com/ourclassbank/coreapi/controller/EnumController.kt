package com.ourclassbank.coreapi.controller

import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "enum")
@RestController
class EnumController {
    @Operation(summary = "용돈계좌 입출금 기록 구분")
    @GetMapping("/api/v1/enum/pocketmoney-account-history-type")
    fun pocketmoneyAccountHistoryType(): List<EnumResponse> {
        return PocketmoneyAccountHistoryType.values().map { EnumResponse(it.name, it.description) }
    }
}

data class EnumResponse(
    val name: String,
    val description: String
)
