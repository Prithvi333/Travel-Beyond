package com.masai.exception;

@SuppressWarnings("serial")
public class EmptyReportListException extends RuntimeException {

	public EmptyReportListException(String message) {
		super(message);
	}
}
