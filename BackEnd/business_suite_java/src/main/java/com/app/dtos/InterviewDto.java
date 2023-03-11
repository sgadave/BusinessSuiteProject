package com.app.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.app.entities.custom_enum.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InterviewDto {
	private Long applicantId;
	private Long interviewerId;
	private Long jobId;
	private LocalDate interviewDate;
	private LocalTime interviewTime;
	private Status status;
}
