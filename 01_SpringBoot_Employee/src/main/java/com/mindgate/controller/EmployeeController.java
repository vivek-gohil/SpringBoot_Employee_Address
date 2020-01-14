package com.mindgate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.pojo.Employee;
import com.mindgate.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public boolean addNewEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT)
	public boolean updateEmployee(@PathVariable("employeeId") int employeeId, @RequestBody Employee employee) {
		return employeeService.updateEmployee(employeeId, employee);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
	public boolean deleteEmployee(@PathVariable("employeeId") int employeeId) {
		return employeeService.deleteEmployee(employeeId);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable("employeeId") int employeeId) {
		return employeeService.getEmployee(employeeId);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

}
