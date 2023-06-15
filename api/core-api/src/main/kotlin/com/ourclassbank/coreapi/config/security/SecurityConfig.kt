package com.ourclassbank.coreapi.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { request ->
            request
                .requestMatchers("/api/v1/test/auth/user").hasRole(RoleType.ROLE_USER.removePrefix())
                .requestMatchers("/api/v1/test/auth/banker").hasRole(RoleType.ROLE_BANKER.removePrefix())
                .requestMatchers("/api/v1/test/**").permitAll()
                .anyRequest().authenticated()
        }

        return http.build()
    }
}

private fun RoleType.removePrefix(): String {
    return this.name.removePrefix("ROLE_")
}
