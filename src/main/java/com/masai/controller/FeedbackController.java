package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Feedback;
import com.masai.service.FeedbackOps;

import jakarta.validation.Valid;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class FeedbackController {
	
	@Autowired
	FeedbackOps f;
	
	@PostMapping("/feedback/{CutomerId}")
	public ResponseEntity<Feedback> addfeedback(@PathVariable("CutomerId") Integer id,@RequestBody @Valid Feedback f1){
		
		return new ResponseEntity<>(f.addFeedback(id, f1),HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/feedback/{FeedbackId}")
	public ResponseEntity<Feedback> getFeedbackbyFeedbackId(@PathVariable("FeedbackId") Integer id){
		return new ResponseEntity<>(f.findFeedbackbyFeedbackId(id),HttpStatus.OK);
	}
	
	@GetMapping("/feedback/customer/{customerId}")
	public ResponseEntity<Feedback> getFeedbackbyCustomerId(@PathVariable("customerId") Integer id){
		return new ResponseEntity<>(f.findFeedbackbyCustomerId(id),HttpStatus.OK);
	}
	
	@GetMapping("/feedback")
	public ResponseEntity<List<Feedback>> getAllFeedback(){
		return new ResponseEntity<>(f.getAllFeedback(),HttpStatus.OK);
	}
	
	
}
