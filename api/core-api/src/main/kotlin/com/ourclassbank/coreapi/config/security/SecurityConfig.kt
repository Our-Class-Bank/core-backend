package com.ourclassbank.coreapi.config.security

import com.ourclassbank.coreapi.config.security.jwt.JwtAuthenticationFilter
import com.ourclassbank.coredomain.support.jwt.JwtTokenProvider
import com.ourclassbank.modeldomain.user.RoleType
import com.ourclassbank.modeldomain.user.RoleType.ROLE_BANKER
import com.ourclassbank.modeldomain.user.RoleType.ROLE_CREDIT_EVALUATOR
import com.ourclassbank.modeldomain.user.RoleType.ROLE_STUDENT
import com.ourclassbank.modeldomain.user.RoleType.ROLE_TEACHER
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
                    // swagger, actuator
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**").permitAll()
                    .requestMatchers("/actuator/**").permitAll()

                    // 인증, 인가
                    .requestMatchers("/api/v1/auth/**").permitAll()

                    // 회원 공통 기능
                    .requestMatchers("/api/v1/user/**").hasAnyRole(
                        ROLE_STUDENT.toSecurityRole(),
                        ROLE_TEACHER.toSecurityRole()
                    )

                    // 신용평가
                    .requestMatchers("/api/v1/credit-score/**").hasRole(ROLE_CREDIT_EVALUATOR.toSecurityRole())

                    // 용돈 계좌
                    .requestMatchers("/api/v1/account/pocketmoney/**").hasRole(ROLE_BANKER.toSecurityRole())
                    .requestMatchers("/api/v1/account/pocketmoney-history/by-banker").hasRole(ROLE_BANKER.toSecurityRole())
                    .requestMatchers("/api/v1/account/pocketmoney-history/**").hasRole(ROLE_STUDENT.toSecurityRole())

                    // 테스트
                    .requestMatchers("/api/v1/test/auth/student").hasRole(ROLE_STUDENT.toSecurityRole())
                    .requestMatchers("/api/v1/test/auth/banker").hasRole(ROLE_BANKER.toSecurityRole())
                    .requestMatchers("/api/v1/test/auth/teacher").hasRole(ROLE_TEACHER.toSecurityRole())
                    .requestMatchers("/api/v1/test/**").permitAll()
                    .requestMatchers("/api/v1/enum/**").permitAll()

                    // 선생님에게는 전체 권한
                    .requestMatchers("/api/v1/**").hasRole(ROLE_TEACHER.toSecurityRole())

                    .anyRequest().authenticated()
            }
            .csrf { it.disable() }
            .cors { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .addFilterBefore(
                JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )


        return http.build()
    }

    /**
     * security 가 role 을 해석하기 위해서는 ROLE_ prefix 가 없어야 합니다.
     */
    private fun RoleType.toSecurityRole(): String {
        return this.name.removePrefix("ROLE_")
    }
}
