package com.abdulwadud.quiz_app.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
public class Quiz {
    // Setter method
    // Getter method
    @Setter
    @Getter
    private String examType;

    // Setter method for numQ
    // Getter method for numQ
    @Setter
    @Getter
    @Column(name = "numq")
    private Integer numQ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @ManyToMany
    private List<examQuestions> questions;
}
