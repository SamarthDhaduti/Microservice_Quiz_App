package com.example.quizservicequizapp.Feign;

import com.example.quizservicequizapp.Models.QuestionWrapper;
import com.example.quizservicequizapp.Models.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QuestionServiceQuizApp")
public interface QuizInterface {
    //Generate quiz by giving the questions based on number of questions and category given by the Quiz Service
    //it will return the list of question ids then you have to fetch the questions using those ids
    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam int numQuestions);


    // Now using the the above list of question ids get questions for those ids
    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    //Now we have to calculate the score also in the question service only then pass it to client
    // client will send you the list of responses which contains question id and the response given by the user
    // you have to compare it with the right answer and get the score
    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
