package com.ourclassbank.coreapi.controller.util

import com.ourclassbank.coreapi.controller.util.response.EnumResponse
import com.ourclassbank.modeldomain.user.pocketmoneyaccount.PocketmoneyAccountHistoryType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "util api")
@RestController
class UtilController {
    @Operation(summary = "용돈계좌 입출금 기록 구분")
    @GetMapping("/util/api/v1/enum/pocketmoney-account-history-type")
    fun pocketmoneyAccountHistoryType(): List<EnumResponse> {
        return PocketmoneyAccountHistoryType.values().map { EnumResponse(it.name, it.description) }
    }

    @Operation(summary = "500 예외 발생")
    @PostMapping("/util/api/v1/exception/500")
    fun exception500(@RequestBody message: String) {
        throw Exception(message)
    }
}
