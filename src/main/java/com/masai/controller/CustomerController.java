package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Customer;
import com.masai.entity.PackageBooking;
import com.masai.service.BookingOps;
import com.masai.service.CustomerOps;

import jakarta.validation.Valid;

@RestController
public class CustomerController {

	@Autowired
	CustomerOps co;
	@Autowired
	BookingOps bo;

	@PostMapping("/customers")
	public ResponseEntity<String> registerCustomer(@RequestBody @Valid Customer customer) {

		co.addCustomer(customer);

		return new ResponseEntity<>("customer added named " + customer.getCustomerName(), HttpStatus.OK);
	}

	@PostMapping("/packagebooking/{custId}")
	public ResponseEntity<String> customerBooking(@PathVariable int custId,@RequestBody @Valid PackageBooking packageBooking) {
		bo.makeBooking(custId, packageBooking);
		return new ResponseEntity<>("customer added named " + packageBooking.getCustomer().getCustomerName(), HttpStatus.OK);
	}
}
