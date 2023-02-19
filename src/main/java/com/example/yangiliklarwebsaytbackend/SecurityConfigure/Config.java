package com.example.yangiliklarwebsaytbackend.SecurityConfigure;

import com.example.yangiliklarwebsaytbackend.Filter.Filtr;
import com.example.yangiliklarwebsaytbackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Config extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Autowired
    UserService userService;

    @Autowired
    Filtr filtr;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/add").permitAll()
//                .antMatchers("/user/delete/{id}").permitAll()
//                .antMatchers("/user/edit/{id}").permitAll()
//                .antMatchers("/user/read/{id}").permitAll()
//                .antMatchers("/user/read").permitAll()
//                .antMatchers("/lavozim/add").permitAll()
//                .antMatchers("/adminuser/add").permitAll()
//                .antMatchers("/adminuser/edit/{id}").permitAll()
                .antMatchers("/user/tasdiqlash").permitAll()
                .antMatchers("/user/login").permitAll()
//                .antMatchers("/post/addpost").permitAll()
//                .antMatchers("/post/editpost/{id}").permitAll()
//                .antMatchers("/post/delpost/{id}").permitAll()
//                .antMatchers("/post/getpost/{id}").permitAll()
//                .antMatchers("/post/getallposts").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        http
                .addFilterBefore(filtr, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("urazaliabduvaliyev@gmail.com");
        mailSender.setPassword("wkbevuimxspddxyv");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }
}
