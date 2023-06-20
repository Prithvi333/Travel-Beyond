package com.masai.exception;

@SuppressWarnings("serial")
public class DestinationNotFoundException extends RuntimeException {

	public DestinationNotFoundException(String message) {
		super(message);
	}
}
