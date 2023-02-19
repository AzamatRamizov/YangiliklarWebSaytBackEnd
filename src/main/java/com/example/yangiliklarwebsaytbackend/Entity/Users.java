package com.example.yangiliklarwebsaytbackend.Entity;

import com.example.yangiliklarwebsaytbackend.Entity.Abstrack.AbstractEntity;
import com.example.yangiliklarwebsaytbackend.Entity.Enums.Huquqlar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

@EntityListeners(AuditingEntityListener.class)
public class Users  implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(updatable = false)
    @CreatedBy
    private Integer kimTomondanQushilgan;

    @LastModifiedBy
    private Integer kimTomondanTahrirlangan;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String tel;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String code;

    @ManyToOne
    private Lavozim lavozim;

    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Huquqlar> huquqlar = this.lavozim.getHuquqlar();
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for (Huquqlar huquqlar1 : huquqlar) {
            grantedAuthorities.add(new SimpleGrantedAuthority(huquqlar1.name()));
        }
//        for (Huquqlar huquqlar1 : huquqlar) {
//            grantedAuthorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return huquqlar1.name();
//                }
//            });
//        }
        return grantedAuthorities;
    }

    public Users(String name, String lastname, String tel, String username, String password, Lavozim lavozim, boolean enabled) {
        this.name = name;
        this.lastname = lastname;
        this.tel = tel;
        this.username = username;
        this.password = password;
        this.lavozim = lavozim;
        this.enabled = enabled;
    }
}
