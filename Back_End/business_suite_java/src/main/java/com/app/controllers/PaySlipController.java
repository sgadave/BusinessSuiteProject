package com.app.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.PaySlipDto;
import com.app.entities.PaySlip;
import com.app.services.PaySlipService;

@RestController
@RequestMapping("/payslip")
@CrossOrigin(origins = "http://localhost:3000")
public class PaySlipController {

	@Autowired
	private PaySlipService payService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping("/generate_slip/{empId}")
	public ResponseEntity<?> generateSalarySlip(@RequestBody PaySlipDto pay,@PathVariable Long empId){
		PaySlip paySlip = mapper.map(pay, PaySlip.class);
		paySlip.calculateSalary();
		return ResponseEntity.ok(payService.generatePaySlipOfEmployee(empId,paySlip));
	}
	
	@GetMapping("/getallslips/{empId}")
	public ResponseEntity<?> getAllSlipsByEmpId(@PathVariable Long empId){
		return ResponseEntity.ok(payService.getAllPaySlipsById(empId));
	}
	
}
