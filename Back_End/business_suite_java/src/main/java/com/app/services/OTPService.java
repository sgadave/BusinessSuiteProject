package com.app.services;

import com.app.entities.OTP;

public interface OTPService {
	OTP getUserOTPbyUserName(String userName);
}
