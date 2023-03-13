package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dtos.InterviewDto;
import com.app.entities.Applicant;
import com.app.entities.UserCredential;
import com.app.entities.Employee;
import com.app.entities.OTP;
import com.app.entities.OTPGenerator;
import com.app.repositories.ApplicantRepository;
import com.app.repositories.CredentialRepository;
import com.app.repositories.EmailMessageRepository;
import com.app.repositories.EmployeeRepository;
import com.app.repositories.OTPRepository;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private ApplicantRepository appRepo;
	
	@Autowired
	private CredentialRepository credRepo;
	
	@Autowired
	private OTPRepository oTPRepo;
	
	@Override
	public SimpleMailMessage sendPasswordChangeOTPMail(String userName) {
		UserCredential role = credRepo.findById(userName).orElseThrow(()-> new ResourceNotFoundException("Invalid User Name"));
		String otp = OTPGenerator.OTP();
		OTP otpData = new OTP(userName,otp);
		oTPRepo.save(otpData);
		SimpleMailMessage mesg = new SimpleMailMessage();
		if(role.getUserRole().toString().equalsIgnoreCase("ROLE_APPLICANT")) {
			Applicant app = appRepo.findByApplicantCredentialsUserName(userName).orElseThrow(()-> new ResourceNotFoundException("Invalid User Name"));
					mesg.setTo(app.getEmailId());
					mesg.setSubject("OTP for password change request");
					mesg.setText(EmailMessageRepository.passwordChangeOTPEmail(app.getFirstName()+""+app.getMiddleName()+""+app.getLastName(),otp));
			return mesg;
		}else {
			Employee emp = empRepo.findByEmployeeCredentialsUserName(userName).orElseThrow(()-> new ResourceNotFoundException("Invalid User Name"));
			mesg.setTo(emp.getEmailId());
			mesg.setSubject("OTP for password change request");
		 mesg.setText(EmailMessageRepository.passwordChangeOTPEmail(emp.getFirstName()+" "+emp.getMiddleName()+" "+emp.getLastName(),otp));
		 return mesg;
		}
	}

	@Override
	public SimpleMailMessage sendInterviewDetails(InterviewDto intDto) {
		// TODO Auto-generated method stub
		SimpleMailMessage mesg = new SimpleMailMessage();
		Applicant app = appRepo.findById(intDto.getApplicantId()).orElseThrow(()-> new ResourceNotFoundException("Invalid User Name"));
		mesg.setTo(app.getEmailId());
		mesg.setSubject("Confirmation of your virtual interview slot");
		mesg.setText(EmailMessageRepository.shortlistedForInterviewEmail(app.getFirstName()+""+app.getMiddleName()+""+app.getLastName(),intDto.getInterviewDate().toString(),intDto.getInterviewTime().toString()));
		return mesg;
	}

}
