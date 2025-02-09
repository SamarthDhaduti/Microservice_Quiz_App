package com.example.questionservicequizapp.Services;

import com.example.questionservicequizapp.DAO.QuestionDAO;
import com.example.questionservicequizapp.Models.QuestionWrapper;
import com.example.questionservicequizapp.Models.Questions;
import com.example.questionservicequizapp.Models.Response;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public ResponseEntity<List<Questions>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDAO.findByCategory(category),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Questions question) {
        try{
            questionDAO.save(question);
            return new ResponseEntity<>("Successfully added question", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(" ", HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> deleteQuestion(Questions question) {
        try{
            questionDAO.delete(question);
            return new ResponseEntity<>("Successfully deleted question", HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(" ", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, int numQuestions) {
        List<Integer> questionsList = questionDAO.findRandomQuestionsByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(questionsList, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        //We need to return list of QuestionWrapper objects
        List<QuestionWrapper> wrappers = new ArrayList<>();
        //But QuestionDAO return the question object so first make list of that then using this list generate list of wrapper objects
        List<Questions> questions = new ArrayList<>();
        for(Integer questionId : questionIds){
            questions.add(questionDAO.findById(questionId).get());
        }
        //Now from this questionsList update wrapper object
        for(Questions question : questions){

            QuestionWrapper wrapper = new QuestionWrapper();

           wrapper.setId(question.getId());
           wrapper.setQuestionTitle(question.getQuestionTitle());
           wrapper.setOption1(question.getOption1());
           wrapper.setOption2(question.getOption2());
           wrapper.setOption3(question.getOption3());
           wrapper.setOption4(question.getOption4());

            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;
        for(Response response : responses){
            // First get the question according to id present in the response
            Questions questions = questionDAO.findById(response.getId()).get();
            // compare the right answer
            if(response.getResponse().equals(questions.getRightAnswer())){
                right++;
            }
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
