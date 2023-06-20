package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyPackageBookingListException extends RuntimeException {

	public EmptyPackageBookingListException(String message) {
		super(message);
	}
}
