//package com.employee.repository;
//
//import java.time.LocalDate;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.stereotype.Component;
//
//import com.employee.entity.Employee;
//
//// TODO - class for testing only. Needs to be removed on final submission
////@Component
//public class SimpleRepository implements EmployeeRepository {
//	private static Map<Long, Employee> empRepository;
//	
//	public SimpleRepository() {
//		empRepository = new HashMap<Long, Employee>();
//		initEmpRep();
//	}
//
//	private void initEmpRep() {
//		for (Integer i = 1; i<11; i++) {
////			Employee emp = new Employee(i.longValue(), i.toString(), i.toString(), i.toString(), LocalDate.now().withDayOfMonth(i).withYear(1990), LocalDate.now().withDayOfMonth(i).withYear(2014), true);
////			empRepository.put(i.longValue(), emp);
//		}
//		
//	}
//
//	public Employee getEmployeeById(Long id) {
//		return empRepository.get(id);
//	}
//
//	public Long addEmployee(Employee emp) {
//		if (!empRepository.containsKey(emp.getId())) {
//			empRepository.put(emp.getId(), emp);
//		}
//		else {
//			// TODO - need to replace with proper logging.
//			System.out.println("Employee with id: "+emp.getId()+" already exists");
//		}
//	}
//
//	@Override
//	public void deleteEmployee(Long id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void updateEmployee(Employee emp) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Employee> getAllEmployees() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
