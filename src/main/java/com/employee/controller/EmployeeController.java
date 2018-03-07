package com.employee.controller;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.employee.constants.Constants;
import com.employee.entity.Employee;
import com.employee.errorhandling.EmployeeNotFoundException;
import com.employee.errorhandling.Error;
import com.employee.errorhandling.UnsupportedOperationOnEmployee;
import com.employee.repository.EmployeeRepository;

@RestController
@EnableAutoConfiguration
@RequestMapping(value=Constants.EMP_RESOURCE) // TODO - revisit from patterns perspective
public class EmployeeController {
	
	@Autowired
	EmployeeRepository empRep;
	
	
	/**Method to add Employee resource to DB
	 * @param emp
	 * @param ucb
	 * @return ResponseEntity with the URI of the created resource in Header.Location field
	 */
	@PutMapping(consumes=APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> addEmployee(@Valid @RequestBody Employee emp, UriComponentsBuilder ucb) {
		Employee addedEmp = empRep.addEmployee(emp);
		
		// once the resource is successfully added, return its URI for further access
		ResponseEntity<Void> respEntity = createSuccessfulAddResponseHeader(ucb, addedEmp);
		return respEntity;
	}

	/**
	 * @param ucb
	 * @param addedEmp
	 * @return
	 */
	private ResponseEntity<Void> createSuccessfulAddResponseHeader(UriComponentsBuilder ucb, Employee addedEmp) {
		HttpHeaders header = new HttpHeaders();
		URI resourceUri = ucb.path(Constants.EMP_RESOURCE).path("/").path(addedEmp.getId().toString()).build().toUri();
		header.setLocation(resourceUri);
		ResponseEntity<Void> respEntity = new ResponseEntity<Void>(header,HttpStatus.CREATED);
		return respEntity;
	}
	
	/**Get Employee resource by input id
	 * @param id
	 * @return {@link Employee}
	 * @throws {@link EmployeeNotFoundException} in case no employee exists with input id 
	 */
	@GetMapping(produces=APPLICATION_JSON_VALUE, value="/{id}")
	public Employee getEmployee(@PathVariable("id") Long id) {
		Employee emp = empRep.getEmployeeById(id); 
		if (emp == null) {
			throw new EmployeeNotFoundException(id);
		}
		return emp;
	}
	
	/**Delete Employee resource by input id
	 * @param id
	 * @throws {@link EmployeeNotFoundException} in case no employee exists with input id 
	 */
	@DeleteMapping(value= "/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) {
		int numOfRowsDeleted = empRep.deleteEmployee(id);
		if (numOfRowsDeleted==0) {
			throw new EmployeeNotFoundException(id);
		}
	}
	
	/**The input "id" will be matched with the "id" of the employee to be updated.
	 * All fields are updatable except id and status.
	 * The status can only be updated using the delete request.
	 *  
	 * @param emp
	 */
	@PostMapping(consumes=APPLICATION_JSON_VALUE)
	public void updateEmployee(@Valid @RequestBody Employee emp) {
		updateEmployeePreValidation(emp);
		empRep.updateEmployee(emp);
	}
	
	private void updateEmployeePreValidation(Employee iEmp) {
		Employee empFromDb = getEmployee(iEmp.getId());
		if (empFromDb == null) {
			throw new EmployeeNotFoundException(iEmp.getId());
		}
		
		else if (iEmp.getStatus() != empFromDb.getStatus()) {
			throw new UnsupportedOperationOnEmployee("Cannot update \'status\' field");
		}
		
	}

	@GetMapping(path=Constants.ALL_EMP, produces=APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployees(){
	return empRep.getAllEmployees();	
	}
	
	@ExceptionHandler(UnsupportedOperationOnEmployee.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public Error unsupportedOperation(UnsupportedOperationOnEmployee e) {
		Error err = new Error();
		err.setErrorCode("Invalid Request");
		err.setErrorMessage(e.getMessage());
		return err;
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public Error employeeNotFound(EmployeeNotFoundException exc) {
		Error err = new Error();
		err.setErrorCode("Not Found");
		err.setErrorMessage("Employee with id:" + exc.getId()+" was not found");
		return err;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public Error methodArgumentInvalidHandler(MethodArgumentNotValidException e) {
		Error err = new Error();
		err.setErrorCode("Invalid Request");
		//TODO - Error message can be improved. The current message exposes too many details to the caller.
		err.setErrorMessage(e.getMessage());
		return err;
	}

}
