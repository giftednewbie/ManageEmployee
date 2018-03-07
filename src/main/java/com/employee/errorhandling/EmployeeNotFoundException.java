package com.employee.errorhandling;

public class EmployeeNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 3419850297507057276L;
	
	private Long id;

	public EmployeeNotFoundException(Long id) {
		this.id=id;
	}
	
	public Long getId() {
		return id;
	}

}
