package com.masai.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest wr) {

		return new ResponseEntity<>(ex.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<String> noHandlerExceptionFound(NoHandlerFoundException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyCustomersException.class)
	public ResponseEntity<MyError> emptyCustomerException(EmptyCustomersException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<MyError> customerNotFoundException(CustomerNotFoundException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}
}
