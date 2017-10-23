package com.revature.revpro.securityapi.exception;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ErrorResponse {

	private Integer errorCode;
	
	private String message;
}
