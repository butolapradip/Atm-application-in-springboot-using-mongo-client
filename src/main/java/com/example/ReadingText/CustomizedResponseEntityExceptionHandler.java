package com.example.ReadingText;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestController
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	// @ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("in MessageNotReadable ");
		ErrorDetails1 errorDetails = new ErrorDetails1(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		System.out.println("in INTERNAL_SERVER_ERROR ");
		ErrorDetails1 errorDetails = new ErrorDetails1(new Date(), ex.getMessage(), request.getDescription(false));
		System.out.println(ex);
		return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails1 errorDetails = new ErrorDetails1(new Date(), ex.getMessage(), request.getDescription(false));
		System.out.println(ex);
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}
	@Override
	protected ResponseEntity<Object>
	handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request){
		ErrorDetails1 errorDetails = new ErrorDetails1(new Date(), ex.getMessage(), request.getDescription(false));
		System.out.println(ex);
		return new ResponseEntity(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

	/*
	 * @ExceptionHandler(ProfileNotFoundException.class) public final
	 * ResponseEntity<Object> handleUserNotFoundException(ProfileNotFoundException
	 * ex, WebRequest request) { System.out.println("user not found exception");
	 * ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	 * request.getDescription(false)); return new ResponseEntity(errorDetails,
	 * HttpStatus.NOT_FOUND); }
	 * 
	 * @Override protected ResponseEntity<Object>
	 * handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
	 * headers, HttpStatus status, WebRequest request) {
	 * System.out.println("in handleAllExceptions "); ErrorDetails errorDetails =
	 * new ErrorDetails(new Date(), "Validation Failed",
	 * ex.getBindingResult().toString()); return new ResponseEntity(errorDetails,
	 * HttpStatus.BAD_REQUEST); }
	 */

}
