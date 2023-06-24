package com.ourclassbank.coredomain.support.jwt

import com.ourclassbank.modeldomain.user.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${jwt.secret}")
    private val secretKey: String,

    @Value("\${jwt.valid-time}")
    private val validTime: Long,

    private val userDetailsService: UserDetailsService
) {
    fun createToken(user: User): String {
        val now = Date()
        val claims = Jwts.claims().setSubject(user.loginId).apply {
            this["roles"] = user.roles
        }

        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + validTime))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        return userDetailsService.loadUserByUsername(getLoginId(token)).let {
            UsernamePasswordAuthenticationToken(it, "", it.authorities)
        }
    }

    fun getLoginId(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).body.subject
    }

    fun resolveToken(authorizationString: String): String {
        return authorizationString.removePrefix("Bearer ")
    }

    fun validate(token: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
}
