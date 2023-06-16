package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyHotelBookingListException extends RuntimeException {
	public EmptyHotelBookingListException(String message) {
		super(message);
	}
}
