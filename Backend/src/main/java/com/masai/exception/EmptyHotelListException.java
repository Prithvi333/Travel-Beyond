package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyHotelListException extends RuntimeException {
	public EmptyHotelListException(String message) {
		super(message);
	}
}
