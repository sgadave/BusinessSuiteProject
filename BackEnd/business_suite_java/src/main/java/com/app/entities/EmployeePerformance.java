package com.app.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "performancetable")
@Getter
@Setter
public class EmployeePerformance {
	@Id
	@SequenceGenerator(name = "perf_id_generator", sequenceName = "perf_id_seq", initialValue = 41200001, allocationSize = 1)
	@GeneratedValue(generator = "perf_id_generator")
	private Long performanceReportId;
	@Column(name = "report_date")
	private LocalDate reportDate;
	@OneToOne
	@JoinColumn(name = "employee_id")
	private Employee employeePerformance;
	@Column(name = "over_time")
	private LocalTime overTime;
	@Column(name = "feedback_rating")
	private int feedBackRating;
	@Column(name = "team_engagement")
	private int teamEngagement;
	@Column(name = "personal_growth")
	private int personalGrowth;

}
