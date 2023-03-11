package com.app.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.JobDescriptionDto;
import com.app.entities.JobDescription;
import com.app.entities.custom_enum.JobLocation;
import com.app.services.JobDescriptionService;

@RestController
@RequestMapping("/jobdesc")
@CrossOrigin(origins = "http://localhost:3000")
public class JobDescriptionController {

	@Autowired
	private JobDescriptionService jDService;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/jobposts")
	public List<JobDescription> getAllJobDescription(){
		return jDService.getAllJobsPosts();
	}
	
	@PostMapping("/createpost")
	public JobDescription createJobPost(@RequestBody JobDescriptionDto jobPost) {
		JobDescription jobPostDetails = mapper.map(jobPost, JobDescription.class);
		return jDService.createJobPost(jobPostDetails);
	}
	
	@GetMapping("/location/{value}")
	public ResponseEntity<?> getJobsByLocation(@PathVariable String value){
		return ResponseEntity.ok(jDService.getAllJobsPostsByLocation(JobLocation.valueOf(value)));
	}
	@GetMapping("/keyword/{value}")
	public ResponseEntity<?> getJobsByKeyword(@PathVariable String value){
		return ResponseEntity.ok(jDService.getAllJobsPostsByKeyword(value));
	}
	
	@PostMapping("/apply/{jobId}/{appId}")
	public ResponseEntity<?> applyToJob(@PathVariable Long appId,@PathVariable Long jobId ){
		return ResponseEntity.ok(jDService.applyToJob(appId,jobId));
	}
}
