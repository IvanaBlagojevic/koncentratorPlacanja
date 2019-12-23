package com.example.payPalService.exceptions;

public class BadRequest extends RuntimeException {

	public BadRequest() {}
	
	public BadRequest(String text) {
		super(text);
	}
}
