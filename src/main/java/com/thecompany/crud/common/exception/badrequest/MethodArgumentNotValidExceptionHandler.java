package com.thecompany.crud.common.exception.badrequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		return new ResponseEntity<>(
				new CustomizedExceptionHandlerResponse(
						HttpStatus.BAD_REQUEST.value(), 
						ex.getBindingResult().getFieldErrors().stream()
						.map(err -> err.getDefaultMessage())
						.collect(java.util.stream.Collectors.joining(", "))
				),
				HttpStatus.BAD_REQUEST);
	}

}