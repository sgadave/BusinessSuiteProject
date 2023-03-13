package com.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dtos.ApiResponse;
import com.app.entities.Applicant;
import com.app.entities.JobDescription;
import com.app.entities.custom_enum.JobLocation;
import com.app.repositories.ApplicantRepository;
import com.app.repositories.JobDescriptionRepository;

@Service
@Transactional
public class JobDescriptionServiceImpl implements JobDescriptionService {

	@Autowired
	private JobDescriptionRepository jDRepo;
	
	@Autowired
	private ApplicantRepository appRepo;
	
	@Override
	public List<JobDescription> getAllJobsPosts() {
		return jDRepo.findAll();
	}

	@Override
	public JobDescription createJobPost(JobDescription jobPost) {
		return jDRepo.save(jobPost);
	}

	@Override
	public ApiResponse applyToJob(Long appId,Long jobId) {
		// TODO Auto-generated method stub
		Applicant app=appRepo.findById(appId).get();
		JobDescription jobDesc = jDRepo.findById(jobId).get();
		jobDesc.setApplicationsRecieved(jobDesc.getApplicationsRecieved()+1);
		app.addAppliedJob(jobDesc);
//		appRepo.save(app);
		jDRepo.save(jobDesc);
		return new ApiResponse("Applied to Title : " + jobDesc.getJobTitle()+" Job Id : "+ jobDesc.getJobPostId() +" sucessfully");
	}

	@Override
	public List<JobDescription> getAllJobsPostsByLocation(JobLocation val) {
		// TODO Auto-generated method stub
		return jDRepo.findAllByCity(val);
	}

	@Override
	public List<JobDescription> getAllJobsPostsByKeyword(String val) {
		// TODO Auto-generated method stub
		return jDRepo.findAllByKeywords(val);
	}
	
	
}
