package com.app.services;

import com.app.dtos.ApiResponse;
import com.app.entities.EmployeePerformance;

public interface EmployeePerformanceService {
	ApiResponse addEmployeePerformance(EmployeePerformance perf,Long empId);
}
