package com.application.payment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestApiExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		String formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());
		errorResponse.setDate(formattedDate);
		errorResponse.setDetail(exception.getMessage());
		errorResponse.setMessage("The resource was not found in the database");
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, null, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleBeanValidationError(MethodArgumentNotValidException exception, HttpServletRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
		String formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());
		errorResponse.setDate(formattedDate);
		errorResponse.setDetail(exception.getMessage());
		errorResponse.setMessage("A Validation Exception has happened");
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		List<ValidationError> validations = new ArrayList<>();

		for (FieldError fieldError : fieldErrors) {
			ValidationError error = new ValidationError(fieldError.getCode(), fieldError.getDefaultMessage());
			validations.add(error);
			errorResponse.addError(fieldError.getField(), validations);
		}
		return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);
	}

}
