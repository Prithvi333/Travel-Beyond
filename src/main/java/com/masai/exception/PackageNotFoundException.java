package com.masai.exception;

@SuppressWarnings("serial")
public class PackageNotFoundException extends RuntimeException {
	public PackageNotFoundException(String message) {
		super(message);
	}

}
