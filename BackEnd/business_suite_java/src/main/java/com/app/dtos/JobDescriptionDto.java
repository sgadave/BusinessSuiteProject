package com.app.dtos;

import java.time.LocalDate;

import com.app.entities.custom_enum.JobCategory;
import com.app.entities.custom_enum.JobLocation;
import com.app.entities.custom_enum.JobType;
import com.app.entities.custom_enum.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobDescriptionDto {

	private String jobTitle;

	private JobCategory jobCategory;

	private JobType jobType;

	private LocalDate appPostDate;

	private LocalDate appStartDate;

	private LocalDate appStopDate;

	private Double salary;

	private JobLocation city;

	private String eduQualification;

	private Status appStatus;

	private String description;

}
