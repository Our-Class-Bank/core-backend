package com.ourclassbank.coreapi.controller.util

import com.ourclassbank.coreapi.controller.util.response.EnumResponse
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "util api")
@RestController
class UtilController {
    @Operation(summary = "용돈계좌 입출금 기록 구분")
    @GetMapping("/util/api/v1/enum/pocketmoney-account-history-type")
    fun pocketmoneyAccountHistoryType(): List<EnumResponse> {
        return PocketmoneyAccountHistoryType.values().map { EnumResponse(it.name, it.description) }
    }
}
