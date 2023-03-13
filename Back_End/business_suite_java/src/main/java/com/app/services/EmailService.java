package com.app.services;

import org.springframework.mail.SimpleMailMessage;

import com.app.dtos.InterviewDto;

public interface EmailService {
	SimpleMailMessage sendPasswordChangeOTPMail(String userName);
	SimpleMailMessage sendInterviewDetails(InterviewDto intDto);
}
