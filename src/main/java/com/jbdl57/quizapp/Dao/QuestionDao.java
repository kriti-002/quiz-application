package com.jbdl57.quizapp.Dao;

import com.jbdl57.quizapp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> getQuestionsByCategory(String category);

    @Query(value = "SELECT * FROM Question q WHERE q.category=:category ORDER BY RAND() LIMIT :noOfQuestions", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int noOfQuestions);
}
