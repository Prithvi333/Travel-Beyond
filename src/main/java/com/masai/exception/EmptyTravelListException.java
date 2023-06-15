package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyTravelListException extends RuntimeException {
	public EmptyTravelListException(String message) {
		super(message);
	}

}
