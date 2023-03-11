package com.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.app.dtos.ApiResponse;
import com.app.dtos.InterviewDto;
import com.app.entities.Applicant;
import com.app.entities.Employee;
import com.app.entities.Interview;
import com.app.entities.JobDescription;
import com.app.entities.custom_enum.Status;
import com.app.repositories.ApplicantRepository;
import com.app.repositories.EmployeeRepository;
import com.app.repositories.InterviewRepository;
import com.app.repositories.JobDescriptionRepository;


@Service
@Transactional
public class InterviewServiceimpl implements InterviewService {

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private InterviewRepository intRepo;
	
	@Autowired
	private ApplicantRepository appRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private JobDescriptionRepository jDRepo;
	
	@Autowired
	private EmailService emailService;
	
	@Override
	public ApiResponse scheduleApplicantInterviews(InterviewDto intDto) {
		System.out.println(intDto);
		Employee emp = empRepo.findById(intDto.getInterviewerId()).get();
		Applicant app = appRepo.findById(intDto.getApplicantId()).get();
		JobDescription jDesc = jDRepo.findById(intDto.getJobId()).get();
		Interview interview = new Interview();
		interview.setApplicantId(app);
		interview.setInterviewerId(emp);
		interview.setInterviewDate(intDto.getInterviewDate());
		interview.setInterviewTime(intDto.getInterviewTime());
		interview.setJobInterview(jDesc);
		interview.setStatus(Status.INACTIVE);
		intRepo.save(interview);
		SimpleMailMessage mesg = emailService.sendInterviewDetails(intDto);
		System.out.println(mesg);
		sender.send(mesg);
		return new ApiResponse("Interview Scheduled");
	}

	@Override
	public List<Interview> getAllInterviewSchedules(Long id) {
		// TODO Auto-generated method stub
		return intRepo.findAllByApplicantIdApplicantId(id);
	}
	
	@Override
	public List<Interview> getAllEmployeeInterviewSchedules(Long id) {
		// TODO Auto-generated method stub
		return intRepo.findAllByInterviewerIdEmployeeId(id);
	}

	@Override
	public ApiResponse changeInterviewStatus(Long id) {
		// TODO Auto-generated method stub
		Interview interview = intRepo.findByInterviewerIdEmployeeId(id).get();
		interview.setStatus(Status.ACTIVE);
		intRepo.save(interview);
		return new ApiResponse("Activated Interview");
	}

	
}
