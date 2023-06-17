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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Route;
import com.masai.repository.RouteDao;
import com.masai.service.RouteOps;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class RouteController {
	
	@Autowired
	RouteOps r;
	
	@Autowired
	RouteDao routedto;
	
	@PostMapping("/route/{BusId}")
	public ResponseEntity<Route> addRoute(@PathVariable("BusId") Integer id,@RequestBody @Valid Route route){
		return new ResponseEntity<>(r.addRoute(id, route),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/route/{routeID}")
	public ResponseEntity<Route> deleteRoute(@PathVariable("BusId") Integer id){
		return new ResponseEntity<>(r.removeRoute(id),HttpStatus.OK);
	}
	
	@GetMapping("/route/{routeID}")
	public ResponseEntity<Route> getRoute(@PathVariable("BusId") Integer id){
		return new ResponseEntity<>(r.searchRoute(id),HttpStatus.FOUND);
	}
	
	@PutMapping("/route/update")
	public ResponseEntity<Route> updateRoute(RouteDao routedto,@RequestBody @Valid Route route){
		return new ResponseEntity<>(r.updateRoute(routedto, route),HttpStatus.FOUND);
	}
	@GetMapping("/route")
	public ResponseEntity<List<Route>> getAllRoute(){
		return new ResponseEntity<>(r.viewRouteList(),HttpStatus.FOUND);
	}
	
	
	

}
