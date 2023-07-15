package com.ourclassbank.coredomain.support.security

import com.ourclassbank.modeldomain.user.RoleType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


data class UserContext(
    val loginId: String,
    private val uPassword: String,
    val roles: List<RoleType>,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.map { SimpleGrantedAuthority(it.name) }.toMutableList()
    }

    override fun getPassword() = uPassword
    override fun getUsername() = loginId
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
