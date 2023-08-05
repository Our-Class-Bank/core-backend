package com.ourclassbank.coredomain.config

import com.ourclassbank.coredomain.support.security.UserContext
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class AuditorAware : AuditorAware<String> {
    /**
     * todo 자가 비밀번호 변경은 인증이 없이 진행되기에 auditor 가 없습니다.
     *   아직 updatedBy 는 유효하게 쓰이지 않기에 일단 null 처리 합니다.
     */
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.ofNullable(
            SecurityContextHolder.getContext().authentication?.let {
                if (it.principal is UserContext) (it.principal as UserContext).uUsername
                else null
            }
        )
    }
}
