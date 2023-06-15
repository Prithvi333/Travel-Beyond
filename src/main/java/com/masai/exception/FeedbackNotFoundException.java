package com.masai.exception;

@SuppressWarnings("serial")
public class FeedbackNotFoundException extends RuntimeException {
	public FeedbackNotFoundException(String message) {
		super(message);
	}
}
