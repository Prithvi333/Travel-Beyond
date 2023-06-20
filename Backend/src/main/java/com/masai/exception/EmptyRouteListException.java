package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyRouteListException extends RuntimeException {

	public EmptyRouteListException(String message) {
		super(message);
	}
}
