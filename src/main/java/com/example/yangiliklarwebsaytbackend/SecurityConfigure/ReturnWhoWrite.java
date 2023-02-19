package com.example.yangiliklarwebsaytbackend.SecurityConfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class ReturnWhoWrite {
    public AuditorAware auditorAware(){
        return new WhoWrite();
    }
}
