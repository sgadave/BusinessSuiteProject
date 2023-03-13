package com.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.ApplicationCountResponse;
import com.app.entities.JobDescription;
import com.app.services.JobDescriptionService;
import com.app.services.PageVisitsService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

	@Autowired
	private PageVisitsService pageVisits;
	
	@Autowired
	private JobDescriptionService jDService; 
	
	
	@Autowired
	private ModelMapper mapper;
	
	@PutMapping("/addvisits")
	public void addVisits() {
		pageVisits.addPageVisits();
	}
	
	@GetMapping("/allVisits")
	public ResponseEntity<?> getAllVisits(){
		return ResponseEntity.ok(pageVisits.getPageVisits());
	}
	
	@GetMapping("/applicationsrecieved")
	public ResponseEntity<?> getAllapplicationCount(){
		List<JobDescription> appRecieved = jDService.getAllJobsPosts();
		List<ApplicationCountResponse> appRec = new ArrayList<ApplicationCountResponse>();
		for (JobDescription jobDescription : appRecieved) {
			appRec.add(mapper.map(jobDescription, ApplicationCountResponse.class));
		}
		return ResponseEntity.ok(appRec);
	}
	
	
	
}
