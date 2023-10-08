package com.jbdl57.quizapp.Dao;

import com.jbdl57.quizapp.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
