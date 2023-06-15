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

	@ExceptionHandler(EmptyCustomerListException.class)
	public ResponseEntity<MyError> emptyCustomerException(EmptyCustomerListException ex, WebRequest wr) {

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

	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<MyError> bookingNotFoundException(BookingNotFoundException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyBookingListException.class)
	public ResponseEntity<MyError> emptyBookingException(EmptyBookingListException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FeedbackNotFoundException.class)
	public ResponseEntity<MyError> feedbackNotFoundException(FeedbackNotFoundException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyFeedbackListException.class)
	public ResponseEntity<MyError> emptyFeedbackException(EmptyFeedbackListException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}
}
