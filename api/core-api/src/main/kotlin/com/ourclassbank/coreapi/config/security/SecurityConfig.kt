package com.ourclassbank.coreapi.config.security

import com.ourclassbank.coreapi.config.security.jwt.JwtAuthenticationFilter
import com.ourclassbank.coredomain.support.jwt.JwtTokenProvider
import com.ourclassbank.modeldomain.user.RoleType
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider
) {
    private val student = RoleType.ROLE_STUDENT.name.removePrefix("ROLE_")
    private val banker = RoleType.ROLE_BANKER.name.removePrefix("ROLE_")
    private val teacher = RoleType.ROLE_TEACHER.name.removePrefix("ROLE_")
    private val creditEvaluator = RoleType.ROLE_CREDIT_EVALUATOR.name.removePrefix("ROLE_")

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests { request ->
            request
                // swagger, actuator
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-resources/**").permitAll()
                .requestMatchers("/actuator/**").permitAll()

                // 인증, 인가
                .requestMatchers("/api/v1/auth/**").permitAll()

                // 내 정보
                .requestMatchers("/api/v1/my/**").hasAnyRole(student)

                // 같은 반
                .requestMatchers("/api/v1/same-class/**").hasAnyRole(student, teacher)

                // 신용평가
                .requestMatchers("/api/v1/credit-evaluation/**").hasRole(creditEvaluator)

                // 용돈 계좌
                .requestMatchers("/api/v1/account/pocketmoney/**").hasRole(banker)

                // 선생님에게는 전체 권한
                .requestMatchers("/api/v1/**").hasRole(teacher)

                // test api
                .requestMatchers("/test/api/v1/auth/student").hasRole(student)
                .requestMatchers("/test/api/v1/auth/banker").hasRole(banker)
                .requestMatchers("/test/api/v1/auth/teacher").hasRole(teacher)
                .requestMatchers("/test/**").permitAll()

                // util api
                .requestMatchers("/util/**").permitAll()

                .anyRequest().authenticated()
        }
            .csrf { it.disable() }
            .cors { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(STATELESS) }
            .addFilterBefore(
                JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .exceptionHandling { it.authenticationEntryPoint(CustomAuthenticationEntryPoint()) }
            .exceptionHandling { it.accessDeniedHandler(CustomAccessDeniedHandler()) }

        return http.build()
    }

    class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {
        override fun commence(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authenticationException: AuthenticationException
        ) {
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.outputStream.println(("{ \"error\": \"" + authenticationException.message) + "\" }")
        }
    }

    class CustomAccessDeniedHandler : AccessDeniedHandler {
        override fun handle(
            request: HttpServletRequest,
            response: HttpServletResponse,
            accessDeniedException: AccessDeniedException
        ) {
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.outputStream.println(("{ \"error\": \"" + accessDeniedException.message) + "\" }")
        }
    }
}
