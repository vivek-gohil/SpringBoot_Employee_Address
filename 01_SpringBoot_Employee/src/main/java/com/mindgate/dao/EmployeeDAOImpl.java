package com.mindgate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mindgate.pojo.Address;
import com.mindgate.pojo.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AddressDAOImpl addressDAO;

	private String sql;
	private int count;

	@Override
	public boolean addEmployee(Employee employee) {
		sql = "insert into employee_master values(employee_sequence.nextVal,?,?,?,?)";
		Object[] args = new Object[] { employee.getFirstName(), employee.getLastName(),
				employee.getAddress().getAddressId(), employee.getSalary() };

		count = jdbcTemplate.update(sql, args);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public boolean updateEmployee(int employeeId, Employee employee) {
		sql = "update employee_master set first_name=?,last_name=?,address_id=?,salary=? where employee_id=?";
		Object[] args = new Object[] { employee.getFirstName(), employee.getLastName(),
				employee.getAddress().getAddressId(), employee.getSalary(), employeeId };
		count = jdbcTemplate.update(sql, args);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		sql = "delete from employee_master where employee_id=?";
		Object[] args = new Object[] { employeeId };
		count = jdbcTemplate.update(sql, args);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public Employee getEmployee(int employeeId) {
		sql = "select * from employee_master where employee_id=?";
		Object[] args = new Object[] { employeeId };

		Employee employee = jdbcTemplate.queryForObject(sql, args, new EmployeeRowMapper());
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		sql = "select * from employee_master";
		List<Employee> employeeeList = new ArrayList<Employee>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> map : rows) {
			Employee employee = new Employee();
			employee.setEmployeeId(Integer.valueOf(map.get("employee_id").toString()));
			employee.setFirstName(map.get("first_name").toString());
			employee.setLastName(map.get("last_name").toString());
			employee.setSalary(Double.valueOf(map.get("salary").toString()));

			int addressId = Integer.valueOf(map.get("address_id").toString());
			Address address = addressDAO.getAddress(addressId);
			if (address == null)
				employee.setAddress(new Address());
			else
				employee.setAddress(address);

			employeeeList.add(employee);
		}
		return employeeeList;
	}

	class EmployeeRowMapper implements RowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee employee = new Employee();
			employee.setEmployeeId(rs.getInt("employee_id"));
			employee.setFirstName(rs.getString("first_name"));
			employee.setLastName(rs.getString("last_name"));

			int addressId = rs.getInt("address_id");
			Address address = addressDAO.getAddress(addressId);
			if (address == null)
				employee.setAddress(new Address());
			else
				employee.setAddress(address);
			employee.setSalary(rs.getDouble("salary"));
			return employee;
		}

	}

}
