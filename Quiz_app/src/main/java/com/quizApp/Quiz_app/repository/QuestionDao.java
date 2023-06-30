package com.quizApp.Quiz_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quizApp.Quiz_app.model.Question;
@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
	
	List<Question> findQuestionByCategory(String category);
	
	@Query(value="SELECT * FROM question q Where q.category=:category ORDER BY RAND() LIMIT :num",nativeQuery=true)
	List<Question> findRandomQuestionByCategory(String category, int num);
}
