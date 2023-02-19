package com.example.yangiliklarwebsaytbackend.Controller;

import com.example.yangiliklarwebsaytbackend.Dto.APIResponse;
import com.example.yangiliklarwebsaytbackend.Dto.AdminUserDto;
import com.example.yangiliklarwebsaytbackend.Entity.Users;
import com.example.yangiliklarwebsaytbackend.Repository.LavozimRepos;
import com.example.yangiliklarwebsaytbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminuser")
public class AdminUserContr {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;
    @PreAuthorize(value = "hasAuthority('ADDUSER')")
    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody AdminUserDto adminUserDto){
        APIResponse apiResponse=userService.addByAdmin(adminUserDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('EDITUSER')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@RequestBody AdminUserDto adminUserDto,@PathVariable Integer id){
        APIResponse apiResponse=userService.editByAdmin(adminUserDto,id);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.isHolat());
    }
    @PreAuthorize(value = "hasAuthority('DELETEUSER')")
    @DeleteMapping("/del/{id}")
    public HttpEntity<?> del(@PathVariable Integer id){
        APIResponse apiResponse=userService.deleteByAdmin(id);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
