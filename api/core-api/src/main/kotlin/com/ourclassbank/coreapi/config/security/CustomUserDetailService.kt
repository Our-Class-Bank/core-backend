package com.ourclassbank.coreapi.config.security

import com.ourclassbank.coredomain.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return with(userRepository.findByLoginId(username)) {
            UserContext(loginId, password, roles)
        }
    }
}
