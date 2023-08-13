package com.ourclassbank.coreapi.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class OpenAPIConfig {
    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("our-class-bank core-api")
                    .description(
                        "**uptime: ${LocalDateTime.now()}**\n" +
                                "- ISO 8601 DateTime sample=2020-05-05T10:10:10"
                    )
                    .version("v0.5.2")
            )
    }
}
