package com.app.services;

import java.util.List;

import com.app.dtos.ApiResponse;
import com.app.entities.JobDescription;
import com.app.entities.custom_enum.JobLocation;

public interface JobDescriptionService {
	List<JobDescription> getAllJobsPosts();
	List<JobDescription> getAllJobsPostsByLocation(JobLocation value);
	List<JobDescription> getAllJobsPostsByKeyword(String value);
	JobDescription createJobPost(JobDescription jobPost);
	ApiResponse applyToJob(Long appId,Long jobId);
}
