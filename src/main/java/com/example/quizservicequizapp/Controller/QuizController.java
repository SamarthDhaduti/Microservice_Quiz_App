package com.example.quizservicequizapp.Controller;

import com.example.quizservicequizapp.Models.QuestionWrapper;
import com.example.quizservicequizapp.Models.Quiz;
import com.example.quizservicequizapp.Models.QuizDto;
import com.example.quizservicequizapp.Models.Response;
import com.example.quizservicequizapp.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumOfQuestions(),quizDto.getTitle());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<Response> response) {
         return quizService.calculateResult(id,response);
    }
}
