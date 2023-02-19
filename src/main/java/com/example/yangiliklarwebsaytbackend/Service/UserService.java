package com.example.yangiliklarwebsaytbackend.Service;

import com.example.yangiliklarwebsaytbackend.Dto.*;
import com.example.yangiliklarwebsaytbackend.Entity.Abstrack.LavozimConst;
import com.example.yangiliklarwebsaytbackend.Entity.Lavozim;
import com.example.yangiliklarwebsaytbackend.Entity.Users;
import com.example.yangiliklarwebsaytbackend.Repository.LavozimRepos;
import com.example.yangiliklarwebsaytbackend.Repository.UserRepos;
import com.example.yangiliklarwebsaytbackend.Token.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService{
    @Lazy
    @Autowired
    UserRepos userRepos;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    LavozimRepos lavozimRepos;

    @Autowired
    TokenGenerator tokenGenerator;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    AuthenticationManager authenticationManager;

    public APIResponse addUser(UserDto userDto) {
        if(userDto.getPassword().equals(userDto.getRepassword())){
            boolean checkUser = userRepos.existsByUsername(userDto.getUsername());
            if(!checkUser){
                Users users=new Users();
                users.setName(userDto.getName());
                users.setLastname(userDto.getLastname());
                users.setTel(userDto.getTel());
                users.setUsername(userDto.getUsername());
                users.setPassword(passwordEncoder.encode(userDto.getPassword()));
//                Lavozim lavozim=new Lavozim();
//                lavozim.setLavozimNomi(LavozimConst.USER);
//                lavozimRepos.save(lavozim);
                users.setCode(UUID.randomUUID().toString().substring(0,5));
                users.setLavozim(lavozimRepos.findByLavozimNomi(LavozimConst.USER).get());
                if(xabaryuborish(users.getUsername(),users.getCode())){
                    userRepos.save(users);
                    return new APIResponse("Emailga habar yuborildi tasdiqlang.",true);
                }
                return new APIResponse("Someone is wrong please try again later.",false);
            }
            return new APIResponse("Bunday foydalanuvchi mavjud",false);
        }
        return new APIResponse("Parollar mos emas",false);
    }

    public APIResponse deleteUser(Integer id) {
        Optional<Users> byId = userRepos.findById(id);
        if(byId.isPresent()){
            userRepos.deleteById(id);
            return new APIResponse("User o'chirildi",true);
        }
        return new APIResponse("Id topilmadi",false);
    }

    public APIResponse editUser(Integer id, UserDto userDto) {
        Optional<Users> byId = userRepos.findById(id);
        if(byId.isPresent()){
            Users users = byId.get();
            if(userDto.getName()!=null){
                users.setName(userDto.getName());
            }
            if(userDto.getLastname()!=null){
                users.setLastname(userDto.getLastname());
            }
            if(userDto.getTel()!=null){
                users.setTel(userDto.getTel());
            }
            if(userDto.getUsername()!=null){
                users.setUsername(userDto.getUsername());
            }
            if(userDto.getPassword()!=null){
                if(userDto.getPassword().equals(userDto.getRepassword())){
                    users.setPassword(userDto.getPassword());
                }
                else {
                    return new APIResponse("Passwords is uncorrect",false);
                }
            }
            userRepos.save(users);
            return new APIResponse("User was edited succesfully",true);
        }
        return new APIResponse("User isn't found",false);
    }

    public Users readUserById(Integer id) {
        Optional<Users> byId = userRepos.findById(id);
        if(byId.isPresent()){
            Users users = byId.get();
            return users;
        }
        return new Users();
    }

    public APIResponse addByAdmin(AdminUserDto adminUserDto) {
        boolean b = userRepos.existsByUsername(adminUserDto.getUsername());
        if(!b){
            Users users=new Users();
            users.setName(adminUserDto.getName());
            users.setLastname(adminUserDto.getLastname());
            users.setTel(adminUserDto.getTel());
            users.setUsername(adminUserDto.getUsername());
            users.setPassword(passwordEncoder.encode(adminUserDto.getPassword()));
            Optional<Lavozim> byLavozimNomi = lavozimRepos.findByLavozimNomi(adminUserDto.getLavozimNomi());
            if(!byLavozimNomi.isPresent()){
                return new APIResponse("This role is not exist!",false);
            }
            Lavozim lavozim = byLavozimNomi.get();
            users.setLavozim(lavozim);
            users.setEnabled(true);
            userRepos.save(users);
            return new APIResponse("User added succesfully.",true);
        }
        return new APIResponse("This username is already exists",false);

    }

//    public boolean xabaryuborish(String email,String email_kod){
//        try {
//            SimpleMailMessage MailMessage=new SimpleMailMessage();
//            MailMessage.setFrom("Azamat");
//            MailMessage.setTo(email);
//            MailMessage.setSubject("Email tasdiqlash!");
//            MailMessage.setText("<a href='http://localhost:8080/user/tasdiqlash?email="+email+"&email_kod="+email_kod+"'>Tasdiqlash</a>");
//            javaMailSender.send(MailMessage);
//            return  true;
//        }
//        catch (Exception e){
//            e.getStackTrace();
//        }
//        return false;
//    }

    public boolean xabaryuborish(String email,String email_kod){
        try {
            String pathToAttachment="E:\\Css effects\\css only\\index.html  ";
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreply@baeldung.com");
            helper.setTo(email);
            helper.setSubject("Email tasdiqlash");
//            helper.setText("<a href='http://localhost:8080/user/tasdiqlash?email="+email+"&email_kod="+email_kod+"'>Tasdiqlash</a>",true);
            helper.setText("<button style=\"width: 100px;height:50px;box-shadow: 0px 0px 20px blueviolet; background: rgb(113, 0, 218); border: none; margin: 50px 100px; padding: 15px;\"><a href=\"http://localhost:8080/user/tasdiqlash?email="+email+"&email_kod="+email_kod+"\" style=\"text-decoration: none; color: white; font-size: 16px; text-shadow: 0 0 10px white;\">Tasdiqlash</a></button>",true);
//            FileSystemResource file
//                    = new FileSystemResource(new File(pathToAttachment));
//            helper.addAttachment("Invoice", file);

            javaMailSender.send(message);
            return  true;
        }
        catch (Exception e){
            e.getStackTrace();
        }
        return false;
    }

    public APIResponse editByAdmin(AdminUserDto adminUserDto, Integer id) {

        Optional<Users> byId = userRepos.findById(id);
        if(byId.isPresent()){
            Users users = byId.get();
            users.setName(adminUserDto.getName());
            users.setLastname(adminUserDto.getLastname());
            users.setTel(adminUserDto.getTel());
            users.setUsername(adminUserDto.getUsername());
            users.setPassword(passwordEncoder.encode(adminUserDto.getPassword()));
            Optional<Lavozim> byLavozimNomi = lavozimRepos.findByLavozimNomi(adminUserDto.getLavozimNomi());
            if(!byLavozimNomi.isPresent()){
                return new APIResponse("This role is not yet!",false);
            }
            Lavozim lavozim = byLavozimNomi.get();
            users.setLavozim(lavozim);
            userRepos.save(users);
        }
        return new APIResponse("Id is not found",false);
    }

    public APIResponse deleteByAdmin(Integer id) {
        Optional<Users> byId = userRepos.findById(id);
        if(byId.isPresent()){
            userRepos.deleteById(id);
            return new APIResponse("User was deleted successfully",true);
        }
        return new APIResponse("User was not found",false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> byEmail = userRepos.findByUsername(username);
        if (byEmail.isPresent()){
            return byEmail.get();
        }
        return (UserDetails) new UsernameNotFoundException("Not found");
    }

    public APIResponse emailtasdiqlash(String email, String email_kod) {
        Optional<Users> byEmailAndEmailpassword = userRepos.findByUsernameAndCode(email, email_kod);
        if (byEmailAndEmailpassword.isPresent()){
            Users users = byEmailAndEmailpassword.get();
            users.setCode(null);
            users.setEnabled(true);
            userRepos.save(users);
            return new APIResponse("Profile has activated! You may enter your page!!!",true);
        }
        return new APIResponse("Profile has activated already!!!",false);
    }

    public APIResponse login(LoginDto loginDto) {
        try {
            Optional<Users> byUsername = userRepos.findByUsername(loginDto.getUsername());
            Users users = byUsername.get();
            if(users.getCode()==null){
                Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
                Users user = (Users) authenticate.getPrincipal();
                String token = tokenGenerator.token(user.getUsername(), user.getLavozim());
                return new APIResponse("Profile was activated! Your token is: "+token,true);
            }
            return new APIResponse("Profile hasn't activate yet! Activate was send to your email.",false);
        }
        catch (BadCredentialsException badCredentialsException){
            return new APIResponse("Login or password is incorrect",false);
        }
    }

    public APIResponse blockUser(Integer id, BlockDto blockDto) {
        Optional<Users> byId = userRepos.findById(id);
        if(byId.isPresent()){
            Users users = byId.get();
            if(blockDto.isEnabled()){
                users.setEnabled(blockDto.isEnabled());
                return new APIResponse("User actived",true);
            }
            users.setEnabled(blockDto.isEnabled());
            userRepos.save(users);
            return new APIResponse("User blocked",true);
        }
        return new APIResponse("User not found",false);
    }
}


