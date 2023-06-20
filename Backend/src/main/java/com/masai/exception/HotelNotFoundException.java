package com.masai.exception;

@SuppressWarnings("serial")
public class HotelNotFoundException extends RuntimeException {
	public HotelNotFoundException(String message) {
		super(message);
	}
}
