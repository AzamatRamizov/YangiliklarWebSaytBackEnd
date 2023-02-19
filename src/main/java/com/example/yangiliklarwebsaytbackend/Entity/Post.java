package com.example.yangiliklarwebsaytbackend.Entity;

import com.example.yangiliklarwebsaytbackend.Entity.Abstrack.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Post extends AbstractEntity {
    @Column(nullable = false,columnDefinition = "text")
    private String postSarlavhasi;

    @Column(nullable = false,columnDefinition = "text")
    private String postMatni;

}
