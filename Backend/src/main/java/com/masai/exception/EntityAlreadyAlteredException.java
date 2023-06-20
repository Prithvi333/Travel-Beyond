package com.masai.exception;

@SuppressWarnings("serial")
public class EntityAlreadyAlteredException extends RuntimeException {

	public EntityAlreadyAlteredException(String message) {
		super(message);
	}
}
