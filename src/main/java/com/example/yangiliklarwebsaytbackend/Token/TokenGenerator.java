package com.example.yangiliklarwebsaytbackend.Token;

import com.example.yangiliklarwebsaytbackend.Entity.Lavozim;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenGenerator {
    String password="0000";

    public String token(String username, Lavozim lavozim){
        long time=24*60*60*100;
        Date muddat=new Date(System.currentTimeMillis()+time);

        String tokenn = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(muddat)
                .claim("roles",lavozim.getLavozimNomi() )
                .signWith(SignatureAlgorithm.HS512, password)
                .compact();
        return  tokenn;
    }

    public String usernameolish(String token){
        String subject = Jwts
                .parser()
                .setSigningKey(password)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return subject;
    }
}
