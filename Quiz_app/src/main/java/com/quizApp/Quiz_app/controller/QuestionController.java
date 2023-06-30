package com.quizApp.Quiz_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizApp.Quiz_app.model.Question;
import com.quizApp.Quiz_app.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService service;
	
	@GetMapping("allQuestion")
	public ResponseEntity<List<Question>> goToAllQuestion() {
		
		
		
		return service.getAllQuestion();
	}
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
		return service.getQuestionByCategory(category);
	}
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		
		return service.addQuestion(question);
	}
	@DeleteMapping("delete/{id}")
	public String deleteQuestion(@PathVariable Integer id) {
		return service.deleteQuestionById(id);
	}
	@PutMapping("update/{id}")
	public String updateQuestion(@RequestBody Question question,@PathVariable Integer id) {
		service.updateQuestion(question,id);
		return "success";
	}
	
//	@RequestMapping(value="/questionForm",method=RequestMethod.GET)
//	public String questionForm() {
//		return "questionform";
//	}
//	
//	@RequestMapping(value="questionForm",method=RequestMethod.POST)
//	public String addQuestiontoDb(@ModelAttribute Question question,Model model) {
//		System.out.println(question);
//		
//		return "success";
//	}
}
