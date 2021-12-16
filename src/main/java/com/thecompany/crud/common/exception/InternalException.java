package com.thecompany.crud.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private final transient Logger logger = LoggerFactory.getLogger(getClass());

	public InternalException(String message) {
		super(message);
		logger.error(message);
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}

}
