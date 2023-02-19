package com.example.yangiliklarwebsaytbackend.Entity;

import com.example.yangiliklarwebsaytbackend.Entity.Abstrack.AbstractEntity;
import com.example.yangiliklarwebsaytbackend.Entity.Enums.Huquqlar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Lavozim extends AbstractEntity {

    @Column(nullable = false)
    private String lavozimNomi;

//    @Enumerated(EnumType.ORDINAL)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Huquqlar> huquqlar;

    @Column(columnDefinition = "text")
    private String izoh;
}
