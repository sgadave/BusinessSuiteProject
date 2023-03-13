package com.app.services;

import java.util.List;

import com.app.dtos.ApiResponse;
import com.app.dtos.UpdateUserDetailsDto;
import com.app.entities.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployeeDetails();
	Employee getEmployeeDetails(String userName);
	ApiResponse deleteEmployeeDetails(String userName);
	ApiResponse updateUserDetails(UpdateUserDetailsDto user);
}
