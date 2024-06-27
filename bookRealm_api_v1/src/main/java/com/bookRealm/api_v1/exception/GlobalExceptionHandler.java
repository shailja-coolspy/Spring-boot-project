package com.bookRealm.api_v1.exception;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.bookRealm.api_v1.dto.CustomErrorResponse;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<CustomErrorResponse> handleExcetion(CustomException e){
		
		CustomErrorResponse customErrorResponse=new CustomErrorResponse();
		
		customErrorResponse.setSuccess(false);
		customErrorResponse.setCurrentTime(System.currentTimeMillis());
		customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		customErrorResponse.setMessage(e.getMessage());
		
		return new ResponseEntity<>(customErrorResponse,HttpStatus.NOT_FOUND);
		
	}

	
	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<CustomErrorResponse> handleExcetion(InvalidEmailException e){
		
		CustomErrorResponse customErrorResponse=new CustomErrorResponse();
		
		customErrorResponse.setSuccess(false);
		customErrorResponse.setCurrentTime(System.currentTimeMillis());
		customErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		customErrorResponse.setMessage(e.getMessage());
		
		return new ResponseEntity<>(customErrorResponse,HttpStatus.BAD_REQUEST);
		
	}
	
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = ex.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .collect(Collectors.toMap(
	                        fieldError -> fieldError.getField(),
	                        fieldError -> fieldError.getDefaultMessage()
	                ));
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
}
