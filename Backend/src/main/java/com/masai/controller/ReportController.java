package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Report;
import com.masai.service.ReportOps;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class ReportController {
	
	@Autowired
	ReportOps ro;
	
	@PostMapping("/report/{customerId}")
	public ResponseEntity<Report> addReport(@PathVariable("customerID") Integer id,@RequestBody @Valid Report report){
		return new ResponseEntity<>(ro.addReport(id, report),HttpStatus.CREATED);

	}
	
	@DeleteMapping("/report/{reportId}")
	public ResponseEntity<Report> deleteReport(@PathVariable("reportId") Integer id){
		return new ResponseEntity<>(ro.deleteReport(id),HttpStatus.OK);
	}
	
	@GetMapping("/report/{reportId}")
	public ResponseEntity<Report> getReport(@PathVariable("reportId") Integer id){
		return new ResponseEntity<>(ro.viewReport(id),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/reports")
	public ResponseEntity<List<Report>> getAllReport(){
		return new ResponseEntity<>(ro.viewAllReport(),HttpStatus.ACCEPTED);
	}
	

}
