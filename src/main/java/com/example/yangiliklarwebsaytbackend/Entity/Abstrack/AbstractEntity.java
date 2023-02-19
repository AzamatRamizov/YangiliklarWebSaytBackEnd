package com.example.yangiliklarwebsaytbackend.Entity.Abstrack;


import com.example.yangiliklarwebsaytbackend.Entity.Users;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.cache.spi.TimestampsRegion;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.security.Timestamp;


@MappedSuperclass
@Data

@EntityListeners(AuditorAware.class)
public abstract class AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(updatable = false)
    @CreatedBy
    private Integer kimTomondanQushilgan;

    @LastModifiedBy
    private Integer kimTomondanTahrirlangan;

//    @JoinColumn(updatable = false)
//    @CreationTimestamp
//    private Timestamp qachonQushilgan;
//
//    @UpdateTimestamp
//    private Timestamp qachonTahrirlangan;


}
