package com.example.quizservicequizapp.DAO;

import com.example.quizservicequizapp.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDAO extends JpaRepository<Quiz,Integer> {
}
