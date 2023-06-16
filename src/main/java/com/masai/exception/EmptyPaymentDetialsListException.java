package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyPaymentDetialsListException extends RuntimeException {

	public EmptyPaymentDetialsListException(String message) {
		super(message);
	}
}
