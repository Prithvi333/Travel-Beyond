package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Customer;
import com.masai.entity.SubscribedEmail;
import com.masai.service.CustomerOps;

import jakarta.validation.Valid;

/*This controller is very useful to perform 
mostly task relate to the customer*/

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class CustomerController {

	@Autowired
	CustomerOps co;
	

	@PostMapping("/customer/signup")
	public ResponseEntity<Customer> registerCustomer(@RequestBody @Valid Customer customer) {
		Customer c = co.addCustomer(customer);
		return new ResponseEntity<>(c, HttpStatus.OK);
	}
	
	
	@PutMapping("/customer/update/{cid}")
	public ResponseEntity<Customer> updateCustomer( @PathVariable Integer cid, @RequestBody @Valid Customer customer) {
		Customer c = co.updateCustomer(cid,customer);
		return new ResponseEntity<>(c,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/customer/delete/{cid}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer cid) {
		Customer c = co.deleteCustomer(cid);
		return new ResponseEntity<>(c,HttpStatus.OK);
	}
	
	
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") Integer id) {
		Customer c = co.viewCustomerById(id);
		return new ResponseEntity<>(c,HttpStatus.FOUND);
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewAllCustomer(){
		return new ResponseEntity<>(co.viewAllCustomer(),HttpStatus.OK);
	}
	
   @PostMapping("/subscriber")
   public ResponseEntity<String> subscribe(@Valid @RequestBody SubscribedEmail se){
	   return new ResponseEntity<>(co.subsCustomer(se),HttpStatus.OK);
   }
}
