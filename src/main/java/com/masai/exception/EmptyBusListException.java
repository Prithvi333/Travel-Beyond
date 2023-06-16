package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyBusListException extends RuntimeException {

	public EmptyBusListException(String message) {
		super(message);
	}
}
