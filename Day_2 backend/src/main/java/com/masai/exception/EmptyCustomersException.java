package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyCustomersException extends RuntimeException {

	public EmptyCustomersException(String message) {
		super(message);
	}
}
