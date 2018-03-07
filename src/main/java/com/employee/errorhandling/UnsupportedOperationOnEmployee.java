package com.employee.errorhandling;

public class UnsupportedOperationOnEmployee extends RuntimeException {
	private static final long serialVersionUID = -710476750771143011L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public UnsupportedOperationOnEmployee(String message) {
		this.message = message;
	}
}
