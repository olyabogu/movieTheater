package com.epam.exception;

/**
 * @author Olga_Bogutska.
 */
public class MovieException extends RuntimeException {
	private String errorMsg;

	public MovieException(String message) {
		this.errorMsg = message;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
