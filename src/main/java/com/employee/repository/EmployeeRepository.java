package com.employee.repository;

import java.util.List;

import com.employee.entity.Employee;

// TODO - Need to revisit during final implementation
public interface EmployeeRepository {
	public Employee getEmployeeById(Long id);
	public Employee addEmployee(Employee emp);
	public int deleteEmployee(Long id);
	public int updateEmployee(Employee emp);
	public List<Employee> getAllEmployees();
}
