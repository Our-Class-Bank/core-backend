package com.ourclassbank.coreapi.config.security

import com.ourclassbank.coredomain.model.RoleType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { request ->
                request
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**").permitAll()
                    .requestMatchers("/actuator/**").permitAll()
                    .requestMatchers("/api/v1/user/**").permitAll()
                    .requestMatchers("/api/v1/test/auth/user").hasRole(RoleType.ROLE_USER.removePrefix())
                    .requestMatchers("/api/v1/test/auth/banker").hasRole(RoleType.ROLE_BANKER.removePrefix())
                    .requestMatchers("/api/v1/test/**").permitAll()
                    .anyRequest().authenticated()
            }
            .csrf { it.disable() }

        return http.build()
    }
}

private fun RoleType.removePrefix(): String {
    return this.name.removePrefix("ROLE_")
}
