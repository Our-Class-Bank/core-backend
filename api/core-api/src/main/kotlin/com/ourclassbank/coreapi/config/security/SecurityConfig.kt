package com.ourclassbank.coreapi.config.security

import com.ourclassbank.coreapi.config.security.jwt.JwtAuthenticationFilter
import com.ourclassbank.coreapi.config.security.jwt.JwtTokenProvider
import com.ourclassbank.modeldomain.user.RoleType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider
) {
    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { request ->
                request
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**").permitAll()
                    .requestMatchers("/actuator/**").permitAll()
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    .requestMatchers("/api/v1/test/auth/user").hasRole(RoleType.ROLE_USER.removePrefix())
                    .requestMatchers("/api/v1/test/auth/banker").hasRole(RoleType.ROLE_BANKER.removePrefix())
                    .requestMatchers("/api/v1/test/**").permitAll()
                    .anyRequest().authenticated()
            }
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(
                JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )


        return http.build()
    }
}

private fun RoleType.removePrefix(): String {
    return this.name.removePrefix("ROLE_")
}
