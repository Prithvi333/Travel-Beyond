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

import com.masai.entity.PaymentDetails;
import com.masai.service.PaymentDetailsOps;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class PaymentDetailsController {

	@Autowired
	PaymentDetailsOps p;

	@PostMapping("/Payment/{PackageBookingID}/{customerId}")
	public ResponseEntity<PaymentDetails> makePayment(@PathVariable("PackageBookingID") Integer id,
			@PathVariable Integer customerId) {
		return new ResponseEntity<>(p.makePayment(id, customerId), HttpStatus.CREATED);
	}

	@DeleteMapping("/Payment/{PaymentID}/{customerId}")
	public ResponseEntity<PaymentDetails> cancelPayment(@PathVariable("PaymentID") Integer id,@PathVariable Integer customerId) {
		return new ResponseEntity<>(p.canclePayment(id,customerId), HttpStatus.OK);
	}

	@GetMapping("/Payment")
	public ResponseEntity<List<PaymentDetails>> getAllPayment() {
		return new ResponseEntity<>(p.viewPaymentList(), HttpStatus.OK);
	}

}
