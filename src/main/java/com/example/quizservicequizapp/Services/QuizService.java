package com.example.quizservicequizapp.Services;

import com.example.quizservicequizapp.DAO.QuizDAO;
import com.example.quizservicequizapp.Feign.QuizInterface;
import com.example.quizservicequizapp.Models.QuestionWrapper;
import com.example.quizservicequizapp.Models.Quiz;
import com.example.quizservicequizapp.Models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;
    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int noOfQuestions, String title) {
        // Before setting list of questions to a quiz object first fetch the questions from the QuestionDAO and then
        // set it to object of quiz
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,noOfQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIDs(questions);

        quizDAO.save(quiz);

        return new ResponseEntity<>("Successfully created Quiz", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        // Now we got the questions through the quiz object
          Quiz quiz = quizDAO.findById(id).get();
        //Take those questions from the quiz object
        List<Integer> questionIds = quiz.getQuestionsIDs();
        ResponseEntity<List<QuestionWrapper>> questionForUser = quizInterface.getQuestionsFromId(questionIds);
        return questionForUser;
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> response) {
         ResponseEntity<Integer> score = quizInterface.getScore(response);
        return score;
    }
}
