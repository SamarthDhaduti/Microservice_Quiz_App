package com.example.quizservicequizapp.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @ElementCollection // one quiz have many questions ans one question may appear in many quiz's
    private List<Integer> questionsIDs;

}
