package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyCustomerListException extends RuntimeException {

	public EmptyCustomerListException(String message) {
		super(message);
	}
}
