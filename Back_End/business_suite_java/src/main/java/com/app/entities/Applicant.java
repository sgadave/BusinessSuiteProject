package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.app.entities.custom_enum.Gender;
import com.app.entities.custom_enum.ProfileStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "Applicants")
@NoArgsConstructor
public class Applicant extends Person {
	@Id
	@Column(name = "applicant_id")
	@SequenceGenerator(name = "app_id_generator", sequenceName = "app_id_seq", initialValue = 412291000, allocationSize = 1)
	@GeneratedValue(generator = "app_id_generator")
	private Long applicantId;
	@Column(name = "uni_prn")
	private Long prn;
	@Column(name = "email_id", length = 50)
	private String emailId;
	@Column(name = "primary_contact")
	private String priContact;
	@Column(name = "secondary_contact")
	private String secContact;
	@Column(name = "highest_education_profile", length = 50)
	private String highestEducationProfile;
	@Column(name = "12th_score")
	private double score12th;
	@Column(name = "12th_passing_year")
	private LocalDate passingYear12th;
	@Column(name = "10th_passing_year")
	private LocalDate passingYear10th;
	@Column(name = "10th_score")
	private double score10th;
	@Column(name = "experience")
	private int expYears;
	@Enumerated(EnumType.STRING)
	@Column(name = "profile_status",length = 20)
	private ProfileStatus profileStatus;
	@Column(name="resume_path")
	private String resumePath;
	@OneToOne
	@JoinColumn(name = "user_name")
	private UserCredential applicantCredentials;
	
	@OneToMany(mappedBy = "applicantId",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Interview> interview=new ArrayList<>();
	
	public void addInterviewDetails(Interview interviewDetails) {
		interview.add(interviewDetails);
		interviewDetails.setApplicantId(this);
	}
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "applicant_applied_jobs",
	joinColumns = @JoinColumn(name="applicant_id"),
	inverseJoinColumns = @JoinColumn(name="job_id"))
	private List<JobDescription> jobApplied = new ArrayList<JobDescription>() ;

	
	public void addAppliedJob(JobDescription job) {
		jobApplied.add(job);
		job.appliedJobs(this);
	}
	
	public Applicant(String firstName, String middleName, String lastName, String address, LocalDate dob, String gender,
			Long governmentIdNumber, String govIdType, Long prn, String emailId, String priContact, String secContact,
			String highestEducationProfile, double score12th, LocalDate passingYear12th, LocalDate passingYear10th,
			double score10th, int expYears) {
		super(firstName, middleName, lastName, address, dob, Gender.valueOf(gender.toUpperCase()), governmentIdNumber, govIdType);
		this.prn = prn;
		this.emailId = emailId;
		this.priContact = priContact;
		this.secContact = secContact;
		this.highestEducationProfile = highestEducationProfile;
		this.score12th = score12th;
		this.passingYear12th = passingYear12th;
		this.passingYear10th = passingYear10th;
		this.score10th = score10th;
		this.expYears = expYears;
	}

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public Long getPrn() {
		return prn;
	}

	public void setPrn(Long prn) {
		this.prn = prn;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPriContact() {
		return priContact;
	}

	public void setPriContact(String priContact) {
		this.priContact = priContact;
	}

	public String getSecContact() {
		return secContact;
	}

	public void setSecContact(String secContact) {
		this.secContact = secContact;
	}

	public String getHighestEducationProfile() {
		return highestEducationProfile;
	}

	public void setHighestEducationProfile(String highestEducationProfile) {
		this.highestEducationProfile = highestEducationProfile;
	}

	public double getScore12th() {
		return score12th;
	}

	public void setScore12th(double score12th) {
		this.score12th = score12th;
	}

	public LocalDate getPassingYear12th() {
		return passingYear12th;
	}

	public void setPassingYear12th(LocalDate passingYear12th) {
		this.passingYear12th = passingYear12th;
	}

	public LocalDate getPassingYear10th() {
		return passingYear10th;
	}

	public void setPassingYear10th(LocalDate passingYear10th) {
		this.passingYear10th = passingYear10th;
	}

	public double getScore10th() {
		return score10th;
	}

	public void setScore10th(double score10th) {
		this.score10th = score10th;
	}

	public int getExpYears() {
		return expYears;
	}

	public void setExpYears(int expYears) {
		this.expYears = expYears;
	}

	public UserCredential getApplicantCredentials() {
		return applicantCredentials;
	}

	public void setApplicantCredentials(UserCredential applicantCredentials) {
		this.applicantCredentials = applicantCredentials;
	}

	public ProfileStatus getProfileStatus() {
		return profileStatus;
	}

	public void setProfileStatus(String profileStatus) {
		this.profileStatus = ProfileStatus.valueOf(profileStatus.toUpperCase());
	}

	public String getResumePath() {
		return resumePath;
	}

	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}

//	public List<JobDescription> getJobApplied() {
//		return jobApplied;
//	}
//
//	public void setJobApplied(List<JobDescription> jobApplied) {
//		this.jobApplied = jobApplied;
//	}

}
