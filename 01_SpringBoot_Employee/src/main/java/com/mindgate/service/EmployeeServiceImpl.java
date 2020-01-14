package com.mindgate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.dao.EmployeeDAOImpl;
import com.mindgate.pojo.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAOImpl employeeDAO;

	@Override
	public boolean addEmployee(Employee employee) {
		return employeeDAO.addEmployee(employee);
	}

	@Override
	public boolean updateEmployee(int employeeId, Employee employee) {
		return employeeDAO.updateEmployee(employeeId, employee);
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		return employeeDAO.deleteEmployee(employeeId);
	}

	@Override
	public Employee getEmployee(int employeeId) {
		return employeeDAO.getEmployee(employeeId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

}
