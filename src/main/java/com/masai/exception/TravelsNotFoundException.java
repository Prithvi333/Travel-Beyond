package com.masai.exception;

@SuppressWarnings("serial")
public class TravelsNotFoundException extends RuntimeException {

	public TravelsNotFoundException(String message) {
		super(message);
	}
}
