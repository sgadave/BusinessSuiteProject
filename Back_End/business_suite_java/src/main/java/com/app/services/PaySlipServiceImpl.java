package com.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dtos.ApiResponse;
import com.app.entities.Employee;
import com.app.entities.PaySlip;
import com.app.repositories.EmployeeRepository;
import com.app.repositories.PaySlipRepository;

@Service
@Transactional
public class PaySlipServiceImpl implements PaySlipService {

	@Autowired
	private PaySlipRepository payRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public ApiResponse generatePaySlipOfEmployee(Long empId,PaySlip pay) {
		Employee emp = empRepo.findById(empId).get();
		emp.addSalarySlip(pay);
		payRepo.save(pay);
		return new ApiResponse("Generated Pay Slip");
	}

	@Override
	public List<PaySlip> getAllPaySlipsById(Long empId) {
		return payRepo.findAllByEmpId(empId);
	}
	
	
	
}
