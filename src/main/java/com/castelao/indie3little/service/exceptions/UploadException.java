package com.castelao.indie3little.service.exceptions;

public class UploadException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UploadException(String message) {
		super(message);
		this.message = message;

	}


}