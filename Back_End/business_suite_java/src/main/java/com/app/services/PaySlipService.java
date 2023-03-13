package com.app.services;

import java.util.List;

import com.app.dtos.ApiResponse;
import com.app.entities.PaySlip;

public interface PaySlipService {
	ApiResponse generatePaySlipOfEmployee(Long empId,PaySlip pay);
	List<PaySlip> getAllPaySlipsById(Long empId);
}
