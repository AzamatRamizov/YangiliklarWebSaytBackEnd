package com.example.yangiliklarwebsaytbackend.AutoLoader;

import com.example.yangiliklarwebsaytbackend.Entity.Abstrack.LavozimConst;
import com.example.yangiliklarwebsaytbackend.Entity.Enums.Huquqlar;
import com.example.yangiliklarwebsaytbackend.Entity.Lavozim;
import com.example.yangiliklarwebsaytbackend.Entity.Users;
import com.example.yangiliklarwebsaytbackend.Repository.LavozimRepos;
import com.example.yangiliklarwebsaytbackend.Repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.example.yangiliklarwebsaytbackend.Entity.Enums.Huquqlar.*;

@Component

public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepos userRepos;

    @Autowired
    LavozimRepos lavozimRepos;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value(value = "${spring.sql.init.mode}")
    String boshlangich_yuklanish;

    @Override
    public void run(String... args) throws Exception {
        if(boshlangich_yuklanish.equals("always")){
            Huquqlar[] values = Huquqlar.values();
            Lavozim admin = lavozimRepos.save(new Lavozim(
                    LavozimConst.ADMIN,
                    Arrays.asList(values),
                    "Adminlik qilish"
            ));
            Lavozim user = lavozimRepos.save(new Lavozim(
                    LavozimConst.USER,
//                Arrays.asList(Huquqlar.ADDCOMMENT, Huquqlar.DELETEMYCOMMENT, Huquqlar.READCOMMENT, Huquqlar.READPOST)
                    Arrays.asList(ADDCOMMENT,DELETEMYCOMMENT,READCOMMENT,READPOST),
                    "Oddiy User"
            ));
            userRepos.save(new Users(
                    "Admin","Adminov","+998999999999","admin123", passwordEncoder.encode("0000"), admin,true
            ));
            userRepos.save(new Users(
                    "Urazali","Abduvaliyev","+998932816999","rais@gmail.com", passwordEncoder.encode("rais007"), user,true
            ));
        }
    }
}
