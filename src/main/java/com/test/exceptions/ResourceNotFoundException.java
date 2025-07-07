package com.test.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// create a custom exception
	public ResourceNotFoundException() {
		super("Resource not found!!");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
