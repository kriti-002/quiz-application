package com.jbdl57.quizapp.Service;


import com.fasterxml.jackson.databind.SerializationFeature;
import com.jbdl57.quizapp.Dao.QuestionDao;
import com.jbdl57.quizapp.Dao.QuizDao;
import com.jbdl57.quizapp.Model.Question;
import com.jbdl57.quizapp.Model.QuestionWrapper;
import com.jbdl57.quizapp.Model.Quiz;
import com.jbdl57.quizapp.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service

public class QuizService  {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public String createQuiz(@RequestParam("category") String category, @RequestParam("title") String Title, @RequestParam("noOfQuestions") int noOfQuestions){
        List<Question> QuestionsList= questionDao.findRandomQuestionsByCategory(category, noOfQuestions);
        Quiz quiz= new Quiz();
        quiz.setQuestions(QuestionsList);
        quiz.setTitle(Title);
        quizDao.save(quiz);
        return "success";
    }

    public List<QuestionWrapper> getListOfQuestions(Integer id) {
        Quiz q= quizDao.findById(id).get();
        List<Question> lq= q.getQuestions();
        List<QuestionWrapper> lForUser= new ArrayList<>();
        lq.forEach(qs-> {
            QuestionWrapper qw = new QuestionWrapper(qs.getId(), qs.getQuestion(), qs.getOption1(), qs.getOption2(), qs.getOption3(), qs.getOption4());
            lForUser.add(qw);
        });
        return lForUser;
    }

    public Integer calculateResult(Integer id, List<Response> response) {
        Quiz quiz= quizDao.findById(id).get();
        List<Question> lq= quiz.getQuestions();
        int right=0, i=0;
        for(Response r: response){
            if(r.getResponse().equals(lq.get(i).getRightAnswer())) right++;
            i++;
        }
        return right;
    }
}
