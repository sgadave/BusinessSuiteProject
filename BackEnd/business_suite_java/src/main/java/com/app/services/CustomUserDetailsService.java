package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.UserCredential;
import com.app.repositories.CredentialRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	// dep : User Repo
	@Autowired
	private CredentialRepository credRepo;
//This method will be auto invoked by spring sec : DaoAuthProvider
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// get user details from DB by user name
		UserCredential user = credRepo.findByUserName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid UserName!!!!!!!!!!"));
		//=> email verifed --now simply ret user details lifted form DB to the caller
		return new CustomUserDetails(user);
	}

}
