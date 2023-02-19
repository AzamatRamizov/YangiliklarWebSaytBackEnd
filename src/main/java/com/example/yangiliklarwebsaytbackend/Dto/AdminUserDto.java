package com.example.yangiliklarwebsaytbackend.Dto;

import com.example.yangiliklarwebsaytbackend.Entity.Lavozim;
import lombok.Data;

@Data
public class AdminUserDto {
    private String name;
    private String lastname;
    private String tel;
    private String username;
    private String password;
    private String repassword;
    private String lavozimNomi;

}
