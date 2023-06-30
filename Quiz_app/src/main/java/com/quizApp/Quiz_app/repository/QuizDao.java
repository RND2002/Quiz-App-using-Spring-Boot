package com.quizApp.Quiz_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quizApp.Quiz_app.model.Quiz;
@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
