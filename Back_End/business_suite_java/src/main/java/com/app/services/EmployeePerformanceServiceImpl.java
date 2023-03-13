package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.dtos.ApiResponse;
import com.app.entities.Employee;
import com.app.entities.EmployeePerformance;
import com.app.repositories.EmployeePerformanceRepository;
import com.app.repositories.EmployeeRepository;

public class EmployeePerformanceServiceImpl implements EmployeePerformanceService {

	@Autowired
	private EmployeeRepository empRepo;

	@Autowired
	private EmployeePerformanceRepository prefRepo;

	@Override
	public ApiResponse addEmployeePerformance(EmployeePerformance perf, Long empId) {
		Employee emp = empRepo.findById(empId).get();
		emp.addEmployeePerformance(perf);
		prefRepo.save(perf);
		return new ApiResponse("Added Performance Added");
	}

}
