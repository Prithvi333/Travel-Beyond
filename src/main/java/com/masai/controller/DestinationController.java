package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.masai.entity.Bus;
import com.masai.entity.Destination;
import com.masai.service.DestinationOps;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class DestinationController {
	
	@Autowired
	DestinationOps d;
	
//	@PostMapping("/Destination/{busID}")
//	public ResponseEntity<Destination> addDestibnation(@PathVariable("busID") Integer id, @RequestBody @Valid Destination destination){
//		return new ResponseEntity<>(d.addDestination(id, destination),HttpStatus.CREATED);
//	}

	@PostMapping("/Destination")
	public ResponseEntity<Destination> addDestination(@RequestBody @Valid Destination destination){
		return new ResponseEntity<>(d.addDestination(destination),HttpStatus.CREATED);
	}

	@PutMapping("/destination/{desId}")
	public ResponseEntity<Destination> updateDestination(@RequestBody @Valid Destination destination,@PathVariable Integer desId){
		return new ResponseEntity<>(d.updateDestination(destination,desId),HttpStatus.CREATED);
	}
	@PutMapping("/Destination/{DestinationID}")
	public ResponseEntity<Destination> removeDestination(@PathVariable("DestinationID") Integer id){
		return new ResponseEntity<>(d.removeDestination(id),HttpStatus.OK);
	}
	
	@GetMapping("/Destination/{DestinationID}")
	public ResponseEntity<Destination> searchDestination(@PathVariable("DestinationID") Integer id){
		return new ResponseEntity<>(d.searchDestination(id),HttpStatus.FOUND);
	}
	
	@GetMapping("/Destination/travel/{BusID}")
	public ResponseEntity<List<Destination>> getDestinationByBusID(@PathVariable("BusID") Integer id){
		return new ResponseEntity<>(d.viewDestinationByBusId(id),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/Destinations")
	public ResponseEntity<List<Destination>> getAllDestination(){
		return new ResponseEntity<>(d.viewAllDestination(),HttpStatus.ACCEPTED);
	}
}
