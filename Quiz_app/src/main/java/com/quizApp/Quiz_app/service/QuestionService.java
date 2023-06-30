package com.quizApp.Quiz_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizApp.Quiz_app.model.Question;
import com.quizApp.Quiz_app.repository.QuestionDao;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<List<Question>> getAllQuestion(){
		try {
			return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<List<Question>> getQuestionByCategory(String category){
		try {
			return new ResponseEntity<>(questionDao.findQuestionByCategory(category),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<String> addQuestion(Question question) {
		questionDao.save(question);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public String deleteQuestionById(Integer id) {
		// TODO Auto-generated method stub
		questionDao.deleteById(id);
		return "Deleted Successfully";
	}

	public ResponseEntity<Question> updateQuestion(Question question,Integer id) {
		// TODO Auto-generated method stub
		Question ques=questionDao.findById(id).orElseThrow();
		ques.setQuestion(question.getQuestion());
		ques.setCorrectOption(question.getCorrectOption());
		
		Question updatedQuestion=questionDao.save(ques);
		return ResponseEntity.ok(updatedQuestion);
	}
}
