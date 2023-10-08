package com.jbdl57.quizapp.Controller;

import com.jbdl57.quizapp.Model.QuestionWrapper;
import com.jbdl57.quizapp.Model.Response;
import com.jbdl57.quizapp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public String createQuiz(@RequestParam("category") String category, @RequestParam("title") String title, @RequestParam("noOfQuestions") int noOfQuestions){
        return quizService.createQuiz(category, title, noOfQuestions);
    }
    @GetMapping("/get/{id}")
    public List<QuestionWrapper> getListOfQuestions(@PathVariable Integer id){
        return quizService.getListOfQuestions(id);
    }
    @GetMapping("/submit/{id}")
    public Integer submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response){
        return quizService.calculateResult(id, response);
    }
}
