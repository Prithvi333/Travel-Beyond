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

import com.masai.entity.Bus;
import com.masai.service.BusOps;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class BusController {
	
	@Autowired
	BusOps b;
	
	@PostMapping("/Bus/{travelID}")
	public ResponseEntity<Bus> addBus(@PathVariable("travelID") Integer id,@RequestBody @Valid Bus bus){
		return new ResponseEntity<>(b.addBus(id, bus),HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/bus/{travelID}")
	public ResponseEntity<Bus> removeBus(@PathVariable("travelID") Integer id){
		return new ResponseEntity<>(b.removeBus(id),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/bus/{busID}")
	public ResponseEntity<Bus> searchBus(@PathVariable("busID") Integer id){
		return new ResponseEntity<>(b.searchBus(id),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/bus/travel/{travelID}")
	public ResponseEntity<List<Bus>> getBusByTravelID(@PathVariable("travelID") Integer id){
		return new ResponseEntity<>(b.viewBusByTravelsId(id),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/bus")
	public ResponseEntity<List<Bus>> getAllBus(){
		return new ResponseEntity<>(b.viewAllBuses(),HttpStatus.ACCEPTED);
	}
	
	
	

}
