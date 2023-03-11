package com.app.controllers;

import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.ApiResponse;
import com.app.dtos.CredentialDto;
import com.app.dtos.LoginRequest;
import com.app.dtos.LoginResponse;
import com.app.dtos.UpdatePassword;
import com.app.entities.OTP;
import com.app.entities.UserCredential;
import com.app.jwt_utils.JwtUtils;
import com.app.services.CredentialService;
import com.app.services.OTPService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

	@Autowired
	private CredentialService credService;
	
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private AuthenticationManager authMgr;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private OTPService otpService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getUserName(),
				request.getPassword());
		System.out.println("In User Authentcation" + authToken);
		try {
			Authentication auth = authMgr.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
			System.out.println("Authentication Successful" + auth);
			return ResponseEntity.ok(new LoginResponse(jwtUtils.generateJwtToken(auth),
					credService.getUserRole(request.getUserName()).toString().substring(5)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("User authentication Failed", e);
		}
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUpApplicant(@RequestBody CredentialDto cred){
		System.out.println(cred);
		UserCredential appCred = mapper.map(cred, UserCredential.class);
		appCred.setUserRole("ROLE_APPLICANT");
		return ResponseEntity.ok(credService.createApplicantCredentials(appCred));
	}

	@PostMapping("/change_password")
	public ResponseEntity<?> updateUserPassword(@RequestBody UpdatePassword newPassDetails) {
		OTP otp = otpService.getUserOTPbyUserName(newPassDetails.getUserName());
		
		System.out.println("DATABASE OTP :: "+ otp.getOtp().length()+" -- USER OTP ::"+newPassDetails.getOtp().length());
		System.out.println("DATABASE OTP :: "+ otp.getOtp()+" -- USER OTP ::"+newPassDetails.getOtp());
		System.out.println("DATABASE OTP :: "+ Integer.valueOf(otp.getOtp())+" -- USER OTP :: "+Integer.valueOf(newPassDetails.getOtp()));
		
		
		if (Integer.valueOf(otp.getOtp())==Integer.parseInt(newPassDetails.getOtp())) {
			if (LocalTime.now().isBefore(otp.getTime())) {
				return ResponseEntity.ok(credService.updateUserPassword(newPassDetails.getUserName(), newPassDetails.getPassword()));
			} else {
				return ResponseEntity.status(HttpStatus.GONE).body(new ApiResponse("OTP Expired!!!"));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Entered Wrong OTP"));
		}
	}
	
	
}
