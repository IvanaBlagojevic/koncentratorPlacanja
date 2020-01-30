package com.example.kpService.exceptions;

public class BadRequest extends RuntimeException {

	public BadRequest() {}
	
	public BadRequest(String text) {
		super(text);
	}
}
