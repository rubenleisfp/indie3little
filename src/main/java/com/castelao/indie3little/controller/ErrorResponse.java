package com.castelao.indie3little.controller;

public class ErrorResponse {
 
	private String message;
	
	public ErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
