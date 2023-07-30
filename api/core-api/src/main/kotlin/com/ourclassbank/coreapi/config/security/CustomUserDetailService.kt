package com.ourclassbank.coreapi.config.security

import com.ourclassbank.coredomain.repository.UserRepository
import com.ourclassbank.coredomain.support.security.UserContext
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return with(userRepository.findByUser(username)) {
            UserContext(this.username, password, roles)
        }
    }
}
