package com.jbdl57.quizapp.Service;

import com.jbdl57.quizapp.Dao.QuestionDao;
import com.jbdl57.quizapp.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.getQuestionsByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Optional<Question>> getQuestionsById(Integer id) {
        try{
            return new ResponseEntity<>(questionDao.findById(id), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> createQuestion(Question q) {
        try{
            questionDao.save(q);
            return new ResponseEntity<>("success!", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Couldn't add, please try again.", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try{
            questionDao.deleteById(id);
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("couldn't delete", HttpStatus.BAD_REQUEST);

    }

    public void updateQuestion(Question q, Integer id) {
        try{
            Question qid= questionDao.findById(id).get();
            qid.setCategory(q.getCategory());
            qid.setQuestion(q.getQuestion());
            qid.setDifficulty(q.getDifficulty());
            qid.setOption1(q.getOption1());
            qid.setOption2(q.getOption2());
            qid.setOption3(q.getOption3());
            qid.setOption4(q.getOption4());
            qid.setRightAnswer(q.getRightAnswer());
            questionDao.save(qid);
            System.out.println("done");
            //new ResponseEntity<>("updated!", HttpStatus.OK);
            return;
        }catch(Exception e){
            e.printStackTrace();
        }
        //new ResponseEntity<>("couldn't update, try again", HttpStatus.BAD_REQUEST);
    }
}
