package com.example.yangiliklarwebsaytbackend.SecurityConfigure;

import com.example.yangiliklarwebsaytbackend.Entity.Users;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WhoWrite implements AuditorAware<Integer> {
    @Override
    public Optional getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && authentication.isAuthenticated()){
            Users users = (Users) authentication.getPrincipal();
            return Optional.of(users.getId());
        }
        return Optional.empty();
    }
}
