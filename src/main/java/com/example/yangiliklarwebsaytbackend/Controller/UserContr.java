package com.example.yangiliklarwebsaytbackend.Controller;

import com.example.yangiliklarwebsaytbackend.Dto.APIResponse;
import com.example.yangiliklarwebsaytbackend.Dto.LoginDto;
import com.example.yangiliklarwebsaytbackend.Dto.UserDto;
import com.example.yangiliklarwebsaytbackend.Entity.Users;
import com.example.yangiliklarwebsaytbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserContr {
    @Autowired
    UserService userService;
    @PostMapping("/add")
    public HttpEntity<?> add(@RequestBody UserDto userDto){
        APIResponse apiResponse=userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('DELETEUSER')")
    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        APIResponse apiResponse=userService.deleteUser(id);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('EDITUSER')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody UserDto userDto){
        APIResponse apiResponse=userService.editUser(id,userDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PreAuthorize(value = "hasAuthority('READUSER')")
    @GetMapping("/read/{id}")
    public Users readById(@PathVariable Integer id){
        Users users=userService.readUserById(id);
        return users;
    }
    @GetMapping("/tasdiqlash")
    public HttpEntity<?> tasdiqlash(@RequestParam String email,@RequestParam String email_kod){
        APIResponse apiResponse=userService.emailtasdiqlash(email,email_kod);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
        APIResponse apiResponse=userService.login(loginDto);
        return ResponseEntity.status(apiResponse.isHolat()?200:208).body(apiResponse.getHabar());
    }
}
