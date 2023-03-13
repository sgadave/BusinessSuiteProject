package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.OTP;
import com.app.repositories.OTPRepository;

@Service
@Transactional
public class OTPServiceImpl implements OTPService {
	
	@Autowired
	private OTPRepository otpRepo;

	@Override
	public OTP getUserOTPbyUserName(String userName) {
		
		return otpRepo.findById(userName).get();
	}

}
