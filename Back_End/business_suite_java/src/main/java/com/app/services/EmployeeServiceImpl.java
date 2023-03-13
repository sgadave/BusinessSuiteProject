package com.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dtos.ApiResponse;
import com.app.dtos.UpdateUserDetailsDto;
import com.app.entities.Employee;
import com.app.repositories.CredentialRepository;
import com.app.repositories.EmployeeRepository;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private CredentialRepository credRepo;

	
	@Override
	public List<Employee> getAllEmployeeDetails() {
		// TODO Auto-generated method stub
		return empRepo.findAll();
	}

	@Override
	public Employee getEmployeeDetails(String userName) {
		// TODO Auto-generated method stub
		return empRepo.findByEmployeeCredentialsUserName(userName).get();
	}

	@Override
	public ApiResponse deleteEmployeeDetails(String userName) {
		// TODO Auto-generated method stub
		credRepo.deleteById(userName);
		return new ApiResponse("Deleted Employee Details");
	}

	@Override
	public ApiResponse updateUserDetails(UpdateUserDetailsDto user) {
		// TODO Auto-generated method stub

		Employee emp = empRepo.findById(user.getUserId()).get();
		emp.setAddress(user.getCity());
		emp.setFirstName(user.getFirstName());
		emp.setLastName(user.getLastName());
		emp.setMiddleName(user.getMiddleName());
		emp.setEmailId(user.getEmailId());
		empRepo.save(emp);
		return new ApiResponse("Details Updated Sucessfully");
	}

}
