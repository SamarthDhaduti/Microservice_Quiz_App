package com.example.questionservicequizapp.DAO;

import com.example.questionservicequizapp.Models.Questions;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Questions, Integer> {

    List<Questions> findAll();

    List<Questions>  findByCategory(String category);

    //Now you have to write a Native Query to fetch the questions from the table
    @Query(value = "SELECT q.id FROM questions q WHERE category = :category ORDER BY RAND() LIMIT :numQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQuestions);

}
