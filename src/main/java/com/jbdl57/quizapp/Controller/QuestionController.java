package com.jbdl57.quizapp.Controller;

import com.jbdl57.quizapp.Model.Question;
import com.jbdl57.quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;

    //api calls by Admin-- Create, Update, Delete the question
    @PostMapping("/admin/createQuestions")
    public ResponseEntity<String> createQuestion(@RequestBody Question q){
        return questionService.createQuestion(q);
    }

    @PutMapping ("/admin/updateQuestion")
    public void updateQuestion(@RequestBody Question q, @RequestParam("id") Integer id){
        questionService.updateQuestion(q, id);
    }

    @DeleteMapping("/admin/deleteQuestion")
    public ResponseEntity<String> deleteQuestion(@RequestParam("id") Integer id){
        return questionService.deleteQuestion(id);
    }
    //api calls by users-- getAllQuestions, getQuestionById, getQuestionByCategory
    @GetMapping("/question/getAllQuestions")
    public ResponseEntity<List<Question>> getLq(){
        return questionService.getAllQuestions();
    }
    @GetMapping("/question/getAllQuestions/category/{category}")
    public ResponseEntity<List<Question>> getLqByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }
    @GetMapping(value = "/question/getQuestion/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Question>> getQuestion(@PathVariable("id") Integer id) throws Exception {
        return questionService.getQuestionsById(id);
    }
}
