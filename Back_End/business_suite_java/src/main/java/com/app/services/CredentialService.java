package com.app.services;

import java.util.List;

import com.app.dtos.ApiResponse;
import com.app.entities.UserCredential;
import com.app.entities.custom_enum.UserRoles;

public interface CredentialService {
	List<UserCredential> getAllCredentials();
	UserRoles getUserRole(String userName);
	String createApplicantCredentials(UserCredential cred);
	ApiResponse updateUserPassword(String userName,String password);
}
