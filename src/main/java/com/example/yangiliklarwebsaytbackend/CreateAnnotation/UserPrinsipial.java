package com.example.yangiliklarwebsaytbackend.CreateAnnotation;

import com.example.yangiliklarwebsaytbackend.Dto.APIResponse;
import com.example.yangiliklarwebsaytbackend.Entity.Users;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserPrinsipial {
    @Before(value = "@annotation(checkHuquq)")
    public void tekshirish(CheckHuquq checkHuquq){
        Users principal = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean holat=false;
        for (GrantedAuthority authority : principal.getAuthorities()) {
            if(authority.getAuthority().equals(checkHuquq.huquq())){
                holat=!holat;
                break;
            }
        }
        if(!holat){
            throw new ForbiddenException(checkHuquq.huquq(),"Yo'l yopiq");
        }
    }
}
