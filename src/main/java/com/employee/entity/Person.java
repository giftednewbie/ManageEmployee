package com.employee.entity;

import java.time.LocalDate;

// TODO - consider removing
public class Person {

	protected String firstName;
	protected String middleName;
	protected String lastName;
	protected LocalDate doB;
	
	public Person(String firstName, String middleName, String lastName, LocalDate doB) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.doB = doB;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDoB() {
		return doB;
	}
	public void setDoB(LocalDate doB) {
		this.doB = doB;
	} 
	
}
