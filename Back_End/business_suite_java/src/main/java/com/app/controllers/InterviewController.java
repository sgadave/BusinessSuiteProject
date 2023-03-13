package com.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.InterviewDto;
import com.app.entities.Interview;
import com.app.services.InterviewService;

@RestController
@RequestMapping("/interview")
@CrossOrigin(origins = "http://localhost:3000")
public class InterviewController {

	@Autowired
	private InterviewService intService;
	
//	@Autowired
//	private ModelMapper mapper;
	
	@PostMapping("/schedule_interview")
	public ResponseEntity<?> scheduleApplicantInterview(@RequestBody InterviewDto interview){
		return ResponseEntity.ok(intService.scheduleApplicantInterviews(interview));
	}
	
	@GetMapping("/applicant/{applicantId}")
	public ResponseEntity<?> getInterviewSchedule(@PathVariable Long applicantId){
		List<Interview> interviews= intService.getAllInterviewSchedules(applicantId);
		
		List<InterviewDto> interviewsDto = new ArrayList<InterviewDto>();	
		for (Interview interview : interviews) {
			InterviewDto intDto = new InterviewDto();
			System.out.println(intDto.getJobId());
			intDto.setApplicantId(interview.getApplicantId().getApplicantId());
			intDto.setInterviewerId(interview.getInterviewerId().getEmployeeId());
			intDto.setInterviewDate(interview.getInterviewDate());
			intDto.setInterviewTime(interview.getInterviewTime());
			intDto.setJobId(interview.getJobInterview().getJobPostId());
			intDto.setStatus(interview.getStatus());
			interviewsDto.add(intDto);
		}
		return ResponseEntity.ok(interviewsDto);
	}
	
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<?> getEmployeeInterviewSchedule(@PathVariable Long employeeId){
		List<Interview> interviews= intService.getAllEmployeeInterviewSchedules(employeeId);
		List<InterviewDto> interviewsDto = new ArrayList<InterviewDto>();
		InterviewDto intDto = new InterviewDto();
		for (Interview interview : interviews) {
			intDto.setApplicantId(interview.getApplicantId().getApplicantId());
			intDto.setInterviewerId(interview.getInterviewerId().getEmployeeId());
			intDto.setInterviewDate(interview.getInterviewDate());
			intDto.setInterviewTime(interview.getInterviewTime());
			intDto.setStatus(interview.getStatus());
			intDto.setJobId(interview.getJobInterview().getJobPostId());
			interviewsDto.add(intDto);
		}
		return ResponseEntity.ok(interviewsDto);
	}
	
	@PutMapping("/status/{interviewerId}")
	public ResponseEntity<?> changeInterviewStatusToActive(@PathVariable Long interviewerId){
		return ResponseEntity.ok(intService.changeInterviewStatus(interviewerId));
	}
}
