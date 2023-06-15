package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyFeedbackListException extends RuntimeException {
	public EmptyFeedbackListException(String message) {
		super(message);
	}
}
