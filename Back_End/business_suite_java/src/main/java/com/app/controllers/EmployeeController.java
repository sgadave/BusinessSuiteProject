package com.app.controllers;

import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dtos.NameAndIdDto;
import com.app.dtos.UpdateUserDetailsDto;
import com.app.entities.Employee;
import com.app.services.DocsAndImagesHandlingService;
import com.app.services.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DocsAndImagesHandlingService fileService;

	public EmployeeController() {
		System.out.println("In default Constructor of" + getClass());
	}

	@GetMapping("/allemployeedetails")
	public List<Employee> getAllEmployees() {
		return empService.getAllEmployeeDetails();
	}
	@GetMapping("/profile/{userName}")
	public Employee getEmployeeDetails(@PathVariable String userName) {
		return empService.getEmployeeDetails(userName);
	}
	
	@DeleteMapping("/delete/{userName}")
	public ResponseEntity<?> deleteEmployeeDetails(@PathVariable String userName) {
		return ResponseEntity.ok(empService.deleteEmployeeDetails(userName));
	}
	
	@GetMapping("/name/{userName}")
	public NameAndIdDto getEmployeeName(@PathVariable String userName) {
		return new NameAndIdDto(empService.getEmployeeDetails(userName).getFirstName()+" "+empService.getEmployeeDetails(userName).getLastName(),empService.getEmployeeDetails(userName).getEmployeeId());
	}
	
	@PostMapping(value="/upload_profile_image/{empUserName}",consumes="multipart/form-data")
	public ResponseEntity<?> uploadEmployeeProfileImage(@RequestParam MultipartFile imageFile, @PathVariable String empUserName) throws IOException{
		return new ResponseEntity<>(fileService.uploadEmployeeProfileImage(empUserName, imageFile),HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/prof_image/{empUserName}", produces = { MediaType.IMAGE_GIF_VALUE, 
			MediaType.IMAGE_JPEG_VALUE,
			MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<?> getEmployeeProfileImage(@PathVariable String empUserName) throws IOException{
		return new ResponseEntity<>(fileService.getEmployeeProfileImage(empUserName), HttpStatus.OK);
	}
	
	@PutMapping("/updatedetails")
	public ResponseEntity<?> updateUserDetails(@RequestBody UpdateUserDetailsDto user){
		return ResponseEntity.ok(empService.updateUserDetails(user));
	}
}
