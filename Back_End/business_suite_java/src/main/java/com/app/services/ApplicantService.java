package com.app.services;

import java.util.List;

import com.app.dtos.ApiResponse;
import com.app.dtos.UpdateUserDetailsDto;
import com.app.entities.Applicant;

public interface ApplicantService {
	List<Applicant> getAllApplicantDetails();
	Applicant getApplicantDetails(String userName);
	ApiResponse addApplicantDetails(Applicant app,String userName);
	ApiResponse deleteApplicantDetails(String userName);
	ApiResponse updateUserDetails(UpdateUserDetailsDto user);
}
