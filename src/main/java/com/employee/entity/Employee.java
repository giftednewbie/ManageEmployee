package com.employee.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee{
	
	protected Long id;
	
	@NotNull protected Date dateOfEmployment;	
	@NotNull protected Boolean status;	
	@NotNull protected String firstName;
	@NotNull protected String middleName;
	@NotNull protected String lastName;
	@NotNull protected Date doB;
    
	// TODO - constructor may not be required for final implementation
	@JsonCreator
	public Employee(@JsonProperty("firstName") String firstName, @JsonProperty("middleName") String middleName,
			@JsonProperty("lastName") String lastName, @JsonProperty("doB") Date doB, @JsonProperty("dateOfEmployment") Date dateOfEmployment,
			@JsonProperty("status") Boolean status) {
		// the id will be handled by auto increment on the DB
		this.id = null;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.doB = doB;
		// set default value for status, per business logic
		this.status = status!=null ? status:Boolean.TRUE;
		this.dateOfEmployment = dateOfEmployment;
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

	public Date getDoB() {
		return doB;
	}

	public void setDoB(Date doB) {
		this.doB = doB;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateOfEmployment() {
		return dateOfEmployment;
	}

	public void setDateOfEmployment(Date dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
