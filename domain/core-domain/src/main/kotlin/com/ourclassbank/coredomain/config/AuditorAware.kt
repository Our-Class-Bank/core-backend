package com.ourclassbank.coredomain.config

import com.ourclassbank.coredomain.support.security.UserContext
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class AuditorAware : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        SecurityContextHolder.getContext().authentication?.let {
            return Optional.of((it.principal as UserContext).loginId)
        } ?: return Optional.empty()
    }
}
