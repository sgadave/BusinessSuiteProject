package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.app.entities.custom_enum.JobCategory;
import com.app.entities.custom_enum.JobLocation;
import com.app.entities.custom_enum.JobType;
import com.app.entities.custom_enum.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_description")
@NoArgsConstructor
@AllArgsConstructor
public class JobDescription {
	// Generate Custom Job Id for Every Job Post
	@Id
	@SequenceGenerator(name = "job_id_generator", sequenceName = "job_id_seq", initialValue = 1001, allocationSize = 1)
	@GeneratedValue(generator = "job_id_generator")
	private Long jobPostId;

	@Column(name = "job_title")
	private String jobTitle;

	@Column(name = "job_category")
	@Enumerated(EnumType.STRING)
	private JobCategory jobCategory;

	@Enumerated(EnumType.STRING)
	@Column(name = "job_Type")
	private JobType jobType;

	@Column(name = "post_date")
	private LocalDate appPostDate;

	@Column(name = "start_date")
	private LocalDate appStartDate;

	@Column(name = "close_date")
	private LocalDate appStopDate;

	@Column(name = "ctc")
	private Double salary;

	@Column(name = "job_location")
	@Enumerated(EnumType.STRING)
	private JobLocation city;

	@Column(name = "qualification")
	private String eduQualification;
	
	@Column(name = "keywords")
	private String keywords;

	@Column(name = "app_status")
	@Enumerated(EnumType.STRING)
	private Status appStatus;

	@Column(name = "job_desc")
	private String description;

	@Column(name = "applications_recieved")
	private int applicationsRecieved;
	
	
	
	// Association Between Applicant and Job Application
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToMany(mappedBy = "jobApplied")
	private List<Applicant> applicantions = new ArrayList<>();
	
	//Helper Method for applications
	public void appliedJobs(Applicant app) {
		applicantions.add(app);
	}

	// constructor without Job Post Id
	public JobDescription(String jobTitle, String jobCategory, String jobType, LocalDate appPostDate,
			LocalDate appStartDate, LocalDate appStopDate, Double salary, String city, String eduQualification,
			String keywords,String appStatus, String description) {
		super();
		this.jobTitle = jobTitle;
		this.jobCategory = JobCategory.valueOf(jobCategory);
		this.jobType = JobType.valueOf(jobType);
		this.appPostDate = appPostDate;
		this.appStartDate = appStartDate;
		this.appStopDate = appStopDate;
		this.salary = salary;
		this.keywords=keywords;
		this.city = JobLocation.valueOf(city);
		this.eduQualification = eduQualification;
		this.appStatus = Status.valueOf(appStatus);
		this.description = description;
		this.applicationsRecieved=0;
	}

	// Setters And Getters

	
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public JobCategory getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = JobCategory.valueOf(jobCategory);
	}

	public LocalDate getAppPostDate() {
		return appPostDate;
	}

	public void setAppPostDate(LocalDate appPostDate) {
		this.appPostDate = appPostDate;
	}

	public LocalDate getAppStartDate() {
		return appStartDate;
	}

	public void setAppStartDate(LocalDate appStartDate) {
		this.appStartDate = appStartDate;
	}

	public LocalDate getAppStopDate() {
		return appStopDate;
	}

	public void setAppStopDate(LocalDate appStopDate) {
		this.appStopDate = appStopDate;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public JobLocation getCity() {
		return city;
	}

	public void setCity(JobLocation city) {
		this.city = city;
	}

	public String getEduQualification() {
		return eduQualification;
	}

	public void setEduQualification(String eduQualification) {
		this.eduQualification = eduQualification;
	}

	public Status getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(Status appStatus) {
		this.appStatus = appStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getJobPostId() {
		return jobPostId;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = JobType.valueOf(jobType);
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getApplicationsRecieved() {
		return applicationsRecieved;
	}

	public void setApplicationsRecieved(int applicationsRecieved) {
		this.applicationsRecieved = applicationsRecieved;
	}

//	public List<Applicant> getApplicantions() {
//		return applicantions;
//	}
//
//	public void setApplicantions(List<Applicant> applicantions) {
//		this.applicantions = applicantions;
//	}

}
