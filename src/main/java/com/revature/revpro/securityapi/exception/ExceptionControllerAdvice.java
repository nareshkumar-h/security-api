package com.revature.revpro.securityapi.exception;

import javax.persistence.NonUniqueResultException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.VndErrors.VndError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerAdvice {

	@ResponseBody
	@ExceptionHandler(NonUniqueResultException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndError duplicateRecordFound(NonUniqueResultException ex) {
		return new VndError("error", ex.getMessage());
	}
	
	
	@ResponseBody
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndError constraintError(DataIntegrityViolationException ex) {
		return new VndError("error", ex.getMessage());
	}
	
	

	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndError noRecordFound(ResourceNotFoundException ex) {
		return new VndError("error", ex.getMessage());
	}
	
	
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("Please contact your administrator");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
	
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(AccessDeniedException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.FORBIDDEN.value());
		error.setMessage("Access Denied");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.FORBIDDEN);
	}
	
	
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(InvalidTokenException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.FORBIDDEN.value());
		error.setMessage("Access Token Expired");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.FORBIDDEN);
	}
	
}
