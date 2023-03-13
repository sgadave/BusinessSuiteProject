package com.app.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dtos.ApiResponse;
import com.app.dtos.GovIdDto;

public interface DocsAndImagesHandlingService {
	String uploadEmployeeProfileImage(String userName,MultipartFile imageFile) throws IOException;
	ApiResponse uploadApplicantResume(String userName,MultipartFile imageFile) throws IOException;
	String uploadEmployeeGovernmentIdImage(String userName,MultipartFile imageFile) throws IOException;
	String uploadApplicantGovernmentIdImage(String userName,MultipartFile imageFile,GovIdDto govId) throws IOException;
	String uploadApplicantProfileImage(String userName,MultipartFile imageFile) throws IOException;
	byte[] getEmployeeProfileImage(String userName) throws IOException;
	byte[] getEmployeeGovernmentIdImage(String userName) throws IOException;
	byte[] getApplicantProfileImage(String userName) throws IOException;
	byte[] getApplicantGovernmentIdImage(String userName) throws IOException;
	byte[] getApplicantResume(String userName) throws IOException;
}
