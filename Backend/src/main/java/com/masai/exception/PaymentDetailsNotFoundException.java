package com.masai.exception;

@SuppressWarnings("serial")
public class PaymentDetailsNotFoundException extends RuntimeException {

	public PaymentDetailsNotFoundException(String message) {
		super(message);
	}
}
