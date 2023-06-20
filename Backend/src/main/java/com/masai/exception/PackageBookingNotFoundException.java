package com.masai.exception;

@SuppressWarnings("serial")
public class PackageBookingNotFoundException extends RuntimeException {

	public PackageBookingNotFoundException(String message) {
		super(message);
	}
}
