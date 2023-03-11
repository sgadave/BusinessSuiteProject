package com.app.controllers;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dtos.ApplicantDto;
import com.app.dtos.GovIdDto;
import com.app.dtos.NameAndIdDto;
import com.app.dtos.UpdateUserDetailsDto;
import com.app.entities.Applicant;
import com.app.services.ApplicantService;
import com.app.services.DocsAndImagesHandlingService;

@RestController
@RequestMapping("/applicant")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicantController {

	@Autowired
	private ApplicantService appService;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private DocsAndImagesHandlingService fileService;

	public ApplicantController() {
		System.out.println("In default Constructor of" + getClass());
	}

	@GetMapping("/view")
	public List<Applicant> getAllApplicants() {
		return appService.getAllApplicantDetails();
	}
	
	@PostMapping("/{userName}/createprofile")
	public ResponseEntity<?> createApplicantDetails(@PathVariable String userName,@RequestBody ApplicantDto app){
		Applicant appDetails = mapper.map(app, Applicant.class);
		appDetails.setProfileStatus("INCOMPLETE");
		return ResponseEntity.ok(appService.addApplicantDetails(appDetails, userName));
	}
	
	@GetMapping("/profile/{userName}")
	public Applicant getApplicantsDetails(@PathVariable String userName) {
		return appService.getApplicantDetails(userName);
	}
	
	@GetMapping("/name/{userName}")
	public NameAndIdDto getApplicantName(@PathVariable String userName) {
		return new NameAndIdDto(appService.getApplicantDetails(userName).getFirstName()+" "+appService.getApplicantDetails(userName).getLastName(),appService.getApplicantDetails(userName).getApplicantId(),appService.getApplicantDetails(userName).getProfileStatus().toString());
	}
	
	@PostMapping(value="/upload_profile_image/{appUserName}",consumes="multipart/form-data")
	public ResponseEntity<?> uploadApplicantProfileImage(@RequestPart("image") MultipartFile imageFile, @PathVariable String appUserName) throws IOException{
		return new ResponseEntity<>(fileService.uploadApplicantProfileImage(appUserName, imageFile),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{userName}")
	public ResponseEntity<?> deleteApplicantDetails(@PathVariable String userName){
		return ResponseEntity.ok(appService.deleteApplicantDetails(userName));
	}
	
	@PostMapping(value="/upload_resume/{appUserName}",consumes="multipart/form-data")
	public ResponseEntity<?> uploadApplicantResume(@RequestPart("resume") MultipartFile resumeFile, @PathVariable String appUserName) throws IOException{
		return new ResponseEntity<>(fileService.uploadApplicantResume(appUserName, resumeFile),HttpStatus.CREATED);
	}
	
	@PostMapping(value="/upload_gov_id/{appUserName}/{govIdType}/{govIdNo}",consumes="multipart/form-data")
	public ResponseEntity<?> uploadApplicantGovernmentId(@RequestPart("govId") MultipartFile imageFile, @PathVariable String appUserName,@PathVariable String govIdType,@PathVariable Long govIdNo) throws IOException{
		GovIdDto govId = new GovIdDto(govIdNo,govIdType);
		return new ResponseEntity<>(fileService.uploadApplicantGovernmentIdImage(appUserName, imageFile,govId),HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/prof_image/{appUserName}", produces = { MediaType.IMAGE_GIF_VALUE, 
			MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<?> getApplicantProfileImage(@PathVariable String appUserName) throws IOException{
		return new ResponseEntity<>(fileService.getApplicantProfileImage(appUserName), HttpStatus.OK);
	}
	
	@GetMapping(value = "/gov_id/{appUserName}", produces = { MediaType.IMAGE_GIF_VALUE, 
			MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<?> getApplicantGovId(@PathVariable String appUserName) throws IOException{
		return new ResponseEntity<>(fileService.getApplicantGovernmentIdImage(appUserName), HttpStatus.OK);
	}
	
	@GetMapping(value = "/resume/{appUserName}", produces = { MediaType.APPLICATION_PDF_VALUE})
	public ResponseEntity<?> getApplicantResume(@PathVariable String appUserName) throws IOException{
		return new ResponseEntity<>(fileService.getApplicantResume(appUserName), HttpStatus.OK);
	}
	
	@PutMapping("/updatedetails")
	public ResponseEntity<?> updateUserDetails(@RequestBody UpdateUserDetailsDto user){
		return ResponseEntity.ok(appService.updateUserDetails(user));
	}
	
}
