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

import com.masai.entity.Packages;
import com.masai.service.PackageOps;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class PackageController {

	@Autowired
	PackageOps p;
	
	@PostMapping("/Packages/{HotelId}")
	public ResponseEntity<Packages> addPackage(@PathVariable("HotelId") Integer id,@RequestBody @Valid Packages pakage){
		
		return new ResponseEntity<>(p.addPackage(id, pakage),HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/Packages/{packagesId}")
	public ResponseEntity<Packages> deletePackage(@PathVariable("packagesId") Integer id){
		
		return new ResponseEntity<>(p.deletePackage(id),HttpStatus.OK);
		
	}
	
	@GetMapping("/Packages/{packagesId}")
	public ResponseEntity<Packages> getPackage(@PathVariable("packagesId") Integer id){
		
		return new ResponseEntity<>(p.viewPackage(id),HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/Packages")
	public ResponseEntity<List<Packages>> getAllPackage(){
		
		return new ResponseEntity<>(p.viewAllPackages(),HttpStatus.ACCEPTED);
		
	}
	
}
