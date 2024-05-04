package com.upl.nibss.hcmlib.config;

import com.upl.nibss.hcmlib.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by stanlee on 11/03/2018.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class AuditConfig {

    @Bean
    AuditorAware<Employee> auditorProvider() {
        return new AuditorAwareImpl();
    }

}
