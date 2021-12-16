package com.thecompany.crud.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
	}

}
