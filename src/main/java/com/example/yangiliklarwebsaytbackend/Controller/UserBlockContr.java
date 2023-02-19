package com.example.yangiliklarwebsaytbackend.Controller;

import com.example.yangiliklarwebsaytbackend.Dto.APIResponse;
import com.example.yangiliklarwebsaytbackend.Dto.BlockDto;
import com.example.yangiliklarwebsaytbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userblock")
public class UserBlockContr {
    @Autowired
    UserService userService;
    @PreAuthorize(value = "hasAuthority('BLOCKUSER')")
    @PostMapping("/edit")
    public HttpEntity<?> edituser(@PathVariable Integer id, @RequestBody BlockDto blockDto){
        APIResponse apiResponse=userService.blockUser(id,blockDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
