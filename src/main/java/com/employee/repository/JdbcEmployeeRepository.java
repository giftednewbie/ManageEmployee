package com.employee.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {
	private final String SELECT_EMPLOYEE_BY_ID = "select * from employee where id = ? and status = ?"; 
	private final String INSERT_EMPLOYEE = "insert into employee values(?,?,?,?,?,?,?)";
	private final String DELETE_EMPLOYEE = "update employee set status = ? where id = ?";
	private final String UPDATE_EMPLOYEE = "update employee set firstName=?, middleName=?, lastName=?, dateOfBirth=?, dateOfEmployment=? where id=?";
	private final String ALL_EMPLOYEES = "select * from employee where status = ?";
	
	JdbcOperations jdbcOperation;
	
	@Autowired
	public JdbcEmployeeRepository(JdbcOperations jdbcOperation) {
		this.jdbcOperation = jdbcOperation;
	}

	/* Method to get an active employee. The filter on the status is applied in the DAO layer, as this filteration seems to be more about.
	 * the modeling of the data, than about business rules.
	 * 
	 * (non-Javadoc)
	 * @see com.employee.repository.EmployeeRepository#getEmployeeById(java.lang.Long)
	 */
	@Override
	public Employee getEmployeeById(Long id) {
		List<Employee> employeeList = jdbcOperation.query(SELECT_EMPLOYEE_BY_ID, new Object[] {id,Boolean.TRUE}, new EmployeeRowMapper());
		if (!employeeList.isEmpty()) {
			// we expect to have at most one employee, since id is the primary key
			return employeeList.get(0);
		}
		return null;
	}
	
	private static class EmployeeRowMapper implements RowMapper<Employee>{
		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee emp = new Employee(rs.getString("firstName"), rs.getString("middleName"), rs.getString("lastName"),
					rs.getDate("dateOfBirth"), rs.getDate("dateOfEmployment"), rs.getBoolean("status"));
			emp.setId(rs.getLong("id"));
			return emp;
		}
	}

	@Override
	public Employee addEmployee(Employee emp) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
    	jdbcOperation.update(
    	    new PreparedStatementCreator() {
    	        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    	            PreparedStatement pst =
    	                con.prepareStatement(INSERT_EMPLOYEE, new String[] {"id"});
    	            pst.setNull(1, Types.BIGINT);
    	            pst.setString(2, emp.getFirstName());
    	            pst.setString(3, emp.getMiddleName());
    	            pst.setString(4, emp.getLastName());
    	            pst.setDate(5, new Date(emp.getDoB().getTime()));
    	            pst.setDate(6, new Date(emp.getDateOfEmployment().getTime()));
    	            pst.setBoolean(7, emp.getStatus());
    	            return pst;
    	        }
    	    },
    	    keyHolder);
    	
    	emp.setId((Long)keyHolder.getKey());
    	return emp;
	}

	@Override
	public int deleteEmployee(Long id) {
		return jdbcOperation.update(DELETE_EMPLOYEE,Boolean.FALSE,id);
	}

	@Override
	public int updateEmployee(Employee emp) {
		return jdbcOperation.update(UPDATE_EMPLOYEE,
				emp.getFirstName(),
				emp.getMiddleName(),
				emp.getLastName(),
				emp.getDoB(),
				emp.getDateOfEmployment(),
				emp.getId());
	}

	/* Method to get all active employees. The filter on the status is applied in the DAO layer, as this filteration seems to be more about.
	 * the modeling of the data, than about business rules.
	 * 
	 * (non-Javadoc)
	 * @see com.employee.repository.EmployeeRepository#getAllEmployees()
	 */
	@Override
	public List<Employee> getAllEmployees() {
		return jdbcOperation.query(ALL_EMPLOYEES,new Object[] {Boolean.TRUE}, new EmployeeRowMapper());
	}

}
