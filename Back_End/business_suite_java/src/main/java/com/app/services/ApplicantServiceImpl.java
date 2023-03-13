package com.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dtos.ApiResponse;
import com.app.dtos.UpdateUserDetailsDto;
import com.app.entities.Applicant;
import com.app.entities.UserCredential;
import com.app.repositories.ApplicantRepository;
import com.app.repositories.CredentialRepository;
@Service
@Transactional
public class ApplicantServiceImpl implements ApplicantService {
	
	@Autowired
	private ApplicantRepository appRepo;
	
	@Autowired
	private CredentialRepository credRepo;
	
	@Override
	public List<Applicant> getAllApplicantDetails() {
		// TODO Auto-generated method stub
		return appRepo.findAll();
	}

	@Override
	public Applicant getApplicantDetails(String userName) {
		// TODO Auto-generated method stub
		return appRepo.findByApplicantCredentialsUserName(userName).get();
	}

	@Override
	public ApiResponse addApplicantDetails(Applicant app, String userName) {
		UserCredential cred = credRepo.findByUserName(userName).get();
		cred.setApplicantCredentials(app);
		appRepo.save(app);
		return new ApiResponse("Applicant Added Sucessfully");
	}

	@Override
	public ApiResponse deleteApplicantDetails(String userName) {
		// TODO Auto-generated method stub
		credRepo.deleteById(userName);
		return new ApiResponse("Applicant Deleted Sucessfully");
	}


	@Override
	public ApiResponse updateUserDetails(UpdateUserDetailsDto user) {
		// TODO Auto-generated method stub

		Applicant app = appRepo.findById(user.getUserId()).get();
		app.setAddress(user.getCity());
		app.setFirstName(user.getFirstName());
		app.setLastName(user.getLastName());
		app.setMiddleName(user.getMiddleName());
		app.setEmailId(user.getEmailId());
		appRepo.save(app);
		return new ApiResponse("Details Updated Sucessfully");
	}
}
