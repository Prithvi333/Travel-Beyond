package com.masai.exception;

@SuppressWarnings("serial")
public class HotelBookingNotFoundException extends RuntimeException {

	public HotelBookingNotFoundException(String message) {
		super(message);
	}
}
