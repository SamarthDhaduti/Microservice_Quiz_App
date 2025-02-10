package com.example.quizservicequizapp.Models;

import lombok.Data;

// This will helps in sending the required details to the CreateQuiz method
@Data
public class QuizDto {
    private String categoryName;
    private int numOfQuestions;
    private String title;
}
