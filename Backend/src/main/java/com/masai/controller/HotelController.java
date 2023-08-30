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

import com.masai.entity.Hotel;
import com.masai.service.HotelOps;

import jakarta.validation.Valid;

/*controller responsible for providing the functionalities related
to the hotels which are further used to create an package
*/
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class HotelController {

	@Autowired
	HotelOps h;

	@PostMapping("/hotel/{desID}")
	public ResponseEntity<Hotel> addHotel(@PathVariable Integer desID, @RequestBody @Valid Hotel hotel) {

		return new ResponseEntity<>(h.addHotel(desID, hotel), HttpStatus.CREATED);

	}

	@PutMapping("/Hotel/{HotelID}")
	public ResponseEntity<Hotel> removeHotel(@PathVariable("HotelID") Integer id) {
		return new ResponseEntity<>(h.removeHotel(id), HttpStatus.OK);
	}

	@GetMapping("/Hotel/{HotelID}")
	public ResponseEntity<Hotel> searchDestination(@PathVariable("HotelID") Integer id) {
		return new ResponseEntity<>(h.searchHotel(id), HttpStatus.FOUND);
	}

	@GetMapping("/Hotel/Destination/{DestinationID}")
	public ResponseEntity<List<Hotel>> getHotelByDestinationID(@PathVariable("DestinationID") Integer id) {
		return new ResponseEntity<>(h.viewHotelsBydestinationId(id), HttpStatus.ACCEPTED);
	}

	@GetMapping("/Hotel")
	public ResponseEntity<List<Hotel>> getAllHotel() {
		return new ResponseEntity<>(h.viewAllHotels(), HttpStatus.ACCEPTED);
	}
}
