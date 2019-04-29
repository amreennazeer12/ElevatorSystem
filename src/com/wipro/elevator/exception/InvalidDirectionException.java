package com.wipro.elevator.exception;

public class InvalidDirectionException extends Exception {
	
	private String errorMessage;

	public InvalidDirectionException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

}
