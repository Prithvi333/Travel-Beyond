package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyBookingListException extends RuntimeException {
	public EmptyBookingListException(String message) {
		super(message);
	}
}
