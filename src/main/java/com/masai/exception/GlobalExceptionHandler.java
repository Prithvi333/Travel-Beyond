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

	@ExceptionHandler(BusNotFoundException.class)
	public ResponseEntity<MyError> busNotFoundException(BusNotFoundException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyBusListException.class)
	public ResponseEntity<MyError> emptyBusListException(EmptyBusListException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DestinationNotFoundException.class)
	public ResponseEntity<MyError> destinationNotFoundException(DestinationNotFoundException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyDestinationListException.class)
	public ResponseEntity<MyError> emptyDestinationListException(EmptyDestinationListException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<MyError> routeNotFoundException(RouteNotFoundException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyRouteListException.class)
	public ResponseEntity<MyError> emptyRouteListException(EmptyRouteListException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HotelNotFoundException.class)
	public ResponseEntity<MyError> hotelNotFoundException(HotelNotFoundException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyHotelListException.class)
	public ResponseEntity<MyError> emptyHotelListException(EmptyHotelListException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ReportNotFoundException.class)
	public ResponseEntity<MyError> reportNotFoundException(ReportNotFoundException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EmptyReportListException.class)
	public ResponseEntity<MyError> emptyReportListException(EmptyReportListException ex, WebRequest wr) {

		MyError me = new MyError();
		me.setLocalDateTime(LocalDateTime.now());
		me.setMessage(ex.getMessage());
		me.setDetails(wr.getDescription(false));
		return new ResponseEntity<>(me, HttpStatus.BAD_REQUEST);
	}
}
