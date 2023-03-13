package com.app.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.app.entities.custom_enum.Status;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "interview_schedule")
@Getter
@Setter
public class Interview {
	@Id
	@SequenceGenerator(name = "interview_id_generator", sequenceName = "interview_id_seq", initialValue = 512220000, allocationSize = 1)
	@GeneratedValue(generator = "interview_id_generator")
	private Long interviewId;
	
	@OneToOne
	@JoinColumn(name="applicant_id")
	private Applicant applicantId;
	
	@OneToOne
	@JoinColumn(name="job_id")
	private JobDescription jobInterview;
	
	@OneToOne
	@JoinColumn(name="interviewer_id")
	private Employee interviewerId;
	@Column(name = "interview_date")
	private LocalDate interviewDate;
	@Column(name = "interview_time")
	private LocalTime interviewTime;
	
	@Column(name="interview_status")
	@Enumerated(EnumType.STRING)
	private Status status;
}
