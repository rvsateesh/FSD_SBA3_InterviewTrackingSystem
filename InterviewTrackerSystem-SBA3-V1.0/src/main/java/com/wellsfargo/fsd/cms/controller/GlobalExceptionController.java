package com.wellsfargo.fsd.cms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.wellsfargo.fsd.cms.exception.ITSException;

@RestControllerAdvice
public class GlobalExceptionController {
	@ExceptionHandler(ITSException.class)
	public ResponseEntity<String> handleITSException(ITSException exp) {
		return new ResponseEntity<String>(exp.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception exp) {
		return new ResponseEntity<String>(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
