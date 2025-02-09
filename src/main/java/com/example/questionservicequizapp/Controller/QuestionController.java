package com.example.questionservicequizapp.Controller;

import com.example.questionservicequizapp.Models.QuestionWrapper;
import com.example.questionservicequizapp.Models.Questions;
import com.example.questionservicequizapp.Models.Response;
import com.example.questionservicequizapp.Services.QuestionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category) {
          return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question) {
         return questionService.addQuestion(question);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteQuestion(@RequestBody Questions question) {
        return questionService.deleteQuestion(question);
    }

    //Generate quiz by giving the questions based on number of questions and category given by the Quiz Service
    //it will return the list of question ids then you have to fetch the questions using those ids
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam int numQuestions){
         return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }

    // Now using the the above list of question ids get questions for those ids
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds) {
        System.out.println("Received question IDs: " + questionIds);
         return questionService.getQuestionsFromId(questionIds);
    }

    //Now we have to calculate the score also in the question service only then pass it to client
    // client will send you the list of responses which contains question id and the response given by the user
    // you have to compare it with the right answer and get the score
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }

}
