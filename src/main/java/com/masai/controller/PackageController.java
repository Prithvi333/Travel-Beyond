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

import com.masai.entity.PackageBooking;
import com.masai.service.BookingOps;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class PackageController {
	
	@Autowired
	BookingOps b;

	@PostMapping("/package/{CutomerId}")
	public ResponseEntity<PackageBooking> bookPackage(@PathVariable("CutomerId") Integer id,@RequestBody @Valid PackageBooking p){
		PackageBooking p1 = b.makeBooking(id, null);
		return new ResponseEntity<>(p1,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/package/{BookingId}")
	public ResponseEntity<PackageBooking> canclePackage(@PathVariable("BookingId") Integer id){
		return new ResponseEntity<>(b.cancleBooking(id),HttpStatus.OK);
	}
	
	@GetMapping("/package/{BookingId}")
	public ResponseEntity<PackageBooking> getPackage(@PathVariable("BookingId") Integer id){
		return new ResponseEntity<>(b.viewBooking(id),HttpStatus.OK);
	} 
	
	
	@GetMapping("/packages")
	public ResponseEntity<List<PackageBooking>> getAllPackage(){
		return new ResponseEntity<>(b.viewAllBooking(),HttpStatus.OK);
	}
	
}
