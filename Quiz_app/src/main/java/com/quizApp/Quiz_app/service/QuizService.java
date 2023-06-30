package com.quizApp.Quiz_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizApp.Quiz_app.model.Question;
import com.quizApp.Quiz_app.model.QuestionWrapper;
import com.quizApp.Quiz_app.model.Quiz;
import com.quizApp.Quiz_app.model.Response;
import com.quizApp.Quiz_app.repository.QuestionDao;
import com.quizApp.Quiz_app.repository.QuizDao;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int num, String title) {
		List<Question> question=questionDao.findRandomQuestionByCategory(category,num);
		
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(question);
		quizDao.save(quiz);
		return new ResponseEntity<>("success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz=quizDao.findById(id);
		List<Question> questionFromDb =quiz.get().getQuestions();
		List<QuestionWrapper> questionForUser=new ArrayList<>();
		
		for(Question q:questionFromDb) {
			QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption1(),
					q.getOption2(),q.getOption3(),q.getOption4());
			questionForUser.add(qw);
		}
		
		return new ResponseEntity<>(questionForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz=quizDao.findById(id).get();
		
		List<Question> questions=quiz.getQuestions();
		int right=0;
		int i=0;
		for(Response response:responses) {
			if(response.getResponse().equals(questions.get(i).getCorrectOption()))
				right++;
			i++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

	
}
