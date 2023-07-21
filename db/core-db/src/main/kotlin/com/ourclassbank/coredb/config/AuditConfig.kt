package com.ourclassbank.coredb.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing(modifyOnCreate = false, auditorAwareRef = "auditorAware")
class AuditConfig
