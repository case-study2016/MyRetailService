package com.retail.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="BAD REQUEST") //400 - BAD REQUEST
public class BadRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7651009142354316207L;

	/**
	 * 
	 */
	
	public BadRequestException (String msg) {
	super(msg);
}	

}
