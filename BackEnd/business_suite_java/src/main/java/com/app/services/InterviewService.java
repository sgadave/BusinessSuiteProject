package com.app.services;

import java.util.List;

import com.app.dtos.ApiResponse;
import com.app.dtos.InterviewDto;
import com.app.entities.Interview;

public interface InterviewService {
	ApiResponse scheduleApplicantInterviews(InterviewDto intDto);
	List<Interview> getAllInterviewSchedules(Long id);
	List<Interview> getAllEmployeeInterviewSchedules(Long id);
	ApiResponse changeInterviewStatus(Long id);
}
