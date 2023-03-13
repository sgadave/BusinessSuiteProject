package com.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dtos.ApiResponse;
import com.app.entities.UserCredential;
import com.app.entities.custom_enum.UserRoles;
import com.app.repositories.CredentialRepository;
import com.app.repositories.OTPRepository;

@Service
@Transactional
public class CredentialServiceImpl implements CredentialService {

	@Autowired
	private CredentialRepository credRepo;

	@Autowired
	private OTPRepository otpRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<UserCredential> getAllCredentials() {
		// TODO Auto-generated method stub
		return credRepo.findAll();
	}

	@Override
	public UserRoles getUserRole(String userName) {
		// TODO Auto-generated method stub
		return credRepo.findByUserName(userName).get().getUserRole();
	}

	@Override
	public String createApplicantCredentials(UserCredential cred) {
		// Save Applicant Credentials To database after encoding User Password
		cred.setPassword(encoder.encode(cred.getPassword()));
		credRepo.save(cred);
		return cred.getUserName();
	}

	@Override
	public ApiResponse updateUserPassword(String userName, String password) {
		UserCredential cred = credRepo.findByUserName(userName).get();
		System.out.println("Password   ------  "+password);
		cred.setPassword(encoder.encode(password));
		credRepo.save(cred);
		otpRepo.deleteById(userName);
		return new ApiResponse("Updated Password Sucessfully");
	}

}
