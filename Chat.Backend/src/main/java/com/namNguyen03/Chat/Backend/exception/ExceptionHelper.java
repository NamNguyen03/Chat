/**
 * 
 */
package com.namNguyen03.Chat.Backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.*;

/**
 * @author nam
 *
 */
@ControllerAdvice
public class ExceptionHelper {

	@ExceptionHandler(value = { BusinessException.class })
	public ResponseEntity<Object> handleException(BusinessException ex) {
		return new ResponseEntity<Object>(new ExceptionResponse(400, "Bad Request", ex.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	@AllArgsConstructor
	@Data
	private class ExceptionResponse{
		private int status;
		private String error;
		private String message;
	}
}