package com.example.yangiliklarwebsaytbackend.Entity;

import com.example.yangiliklarwebsaytbackend.Entity.Abstrack.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment extends AbstractEntity {

    @Column(nullable = false,columnDefinition = "text")
    private String commentMatni;

    @ManyToOne
    private Post post;



}
