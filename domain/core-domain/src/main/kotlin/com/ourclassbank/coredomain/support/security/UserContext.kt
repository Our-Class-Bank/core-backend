package com.ourclassbank.coredomain.support.security

import com.ourclassbank.modeldomain.user.RoleType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * username, password 는 UserDetails 의 예약어이기에 u 를 붙여서 생성 했습니다.
 */
data class UserContext(
    val uUsername: String,
    private val uPassword: String,
    val roles: List<RoleType>,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.map { SimpleGrantedAuthority(it.name) }.toMutableList()
    }

    override fun getPassword() = uPassword
    override fun getUsername() = uUsername
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
