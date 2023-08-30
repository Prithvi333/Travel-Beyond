package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Travels;
import com.masai.entity.TravelsDto;
import com.masai.service.TravelsOps;

import jakarta.validation.Valid;

/*This controller is responsible for performing all 
the functionalities related to the destinations*/

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class TravelController {
	@Autowired
	TravelsOps t;
	
	
	@PostMapping("/travels")
	public ResponseEntity<Travels> addTravel(@RequestBody @Valid Travels Travel){
		return new ResponseEntity<>(t.addTravels(Travel),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/travels/update/{id}")
	public ResponseEntity<Travels> updateTravel(@PathVariable("id") Integer id,@RequestBody @Valid TravelsDto Travel){
		return new ResponseEntity<>(t.updateTravels(id, Travel),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/travels/remove/{id}")
	public ResponseEntity<Travels> cancelTravel(@PathVariable("id") Integer id){
		return new ResponseEntity<>(t.removeTravels(id),HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/travels/{id}")
	public ResponseEntity<Travels> searchTravels(@PathVariable("id") Integer travelId){
		return new ResponseEntity<>(t.searchTravels(travelId),HttpStatus.OK);
	}
	
	@GetMapping("/travels")
	public ResponseEntity<List<Travels>> viewTravels(){
		return new ResponseEntity<>(t.viewTravels(),HttpStatus.OK);
	}
}
