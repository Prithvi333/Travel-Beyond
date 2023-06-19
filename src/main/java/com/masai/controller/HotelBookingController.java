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


import com.masai.entity.HotelBooking;
import com.masai.service.HotelBookingOps;

import jakarta.validation.Valid;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class HotelBookingController {
	
	@Autowired
	HotelBookingOps h;
	
	@PostMapping("/HotelBooking/{customerID}/{HotelId}")
	public ResponseEntity<HotelBooking> addHotelBooking(@PathVariable("customerID") Integer cid, @PathVariable("HotelID") Integer hid,@RequestBody @Valid HotelBooking hotelbooking){
		return new ResponseEntity<>(h.bookHotel(cid, hid, hotelbooking),HttpStatus.CREATED);
	}
	
	@PutMapping("/HotelBooking/{HotelBookingID}")
	public ResponseEntity<HotelBooking> cancelHotelBooking(@PathVariable("HotelBookingID") Integer id){
		return new ResponseEntity<>(h.cancleBooking(id),HttpStatus.OK);
	}
	
	@GetMapping("/HotelBooking/{HotelBookingID}")
	public ResponseEntity<HotelBooking> getHotelBooking(@PathVariable("HotelBookingID") Integer id){
		return new ResponseEntity<>(h.viewBooking(id),HttpStatus.FOUND);
	}
	
	@GetMapping("/HotelBooking")
	public ResponseEntity<List<HotelBooking>> getAllHotelBooking(){
		return new ResponseEntity<>(h.viewAllBooking(),HttpStatus.ACCEPTED);
	}
	
}
