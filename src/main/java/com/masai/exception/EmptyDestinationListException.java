package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyDestinationListException extends RuntimeException {

	public EmptyDestinationListException(String message) {
		super(message);
	}
}
