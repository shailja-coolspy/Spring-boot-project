package com.bookRealm.api_v1.exception;

public class InvalidEmailException extends RuntimeException{
	   public InvalidEmailException(String message) {
	        super(message);
	    }
}
