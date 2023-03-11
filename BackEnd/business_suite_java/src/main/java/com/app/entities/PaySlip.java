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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "salary_slip")
public class PaySlip {
	@Id
	@SequenceGenerator(name = "pay_id_generator", sequenceName = "pay_id_seq", initialValue = 104122000, allocationSize = 1)
	@GeneratedValue(generator = "pay_id_generator")
	private Long slipId;
	@JoinColumn(name = "employee_id")
	@OneToOne
	private Employee empId;
	@Column(name = "generation_date")
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDate slipGenDate;
	@Column(name = "generation_time")
	@JsonProperty(access = Access.READ_ONLY)
	private LocalTime slipGenTime;
	@Column(name = "home_rent_allowance")
	private Double hra;
	@Column(name = "dearness_allowance")
	private Double da;
	@Column(name = "health_insurance")
	private Double insurance;
	@Column(name = "incentive")
	private Double incentive;
	@Column(name = "tax")
	private Double tax;
	@Column(name="basic_salary")
	private Double basicSal;
	@Column(name = "total_salary")
	private Double totalMonthSal;

	public PaySlip(Employee empId, Double hra, Double da, Double insurance, Double incentive, Double tax,
			Double basicSal) {
		super();
		this.empId = empId;
		this.slipGenDate = LocalDate.now();
		this.slipGenTime = LocalTime.now();
		this.hra = hra;
		this.da = da;
		this.insurance = insurance;
		this.incentive = incentive;
		this.tax = tax;
		this.basicSal=basicSal;
	}

	public void calculateSalary() {
		this.totalMonthSal = basicSal + da + hra + incentive + insurance - tax; 
	}
	
	
	public Double getBasicSal() {
		return basicSal;
	}

	public void setBasicSal(Double basicSal) {
		this.basicSal = basicSal;
	}

	public PaySlip() {
		super();
	}

	public Long getSlipId() {
		return slipId;
	}

	public void setSlipId(Long slipId) {
		this.slipId = slipId;
	}

	public Employee getEmpId() {
		return empId;
	}

	public void setEmpId(Employee empId) {
		this.empId = empId;
	}

	public Double getHra() {
		return hra;
	}

	public void setHra(Double hra) {
		this.hra = hra;
	}

	public Double getDa() {
		return da;
	}

	public void setDa(Double da) {
		this.da = da;
	}

	public Double getInsurance() {
		return insurance;
	}

	public void setInsurance(Double insurance) {
		this.insurance = insurance;
	}

	public Double getIncentive() {
		return incentive;
	}

	public void setIncentive(Double incentive) {
		this.incentive = incentive;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getTotalMonthSal() {
		return totalMonthSal;
	}


	public LocalDate getSlipGenDate() {
		return slipGenDate;
	}

	public LocalTime getSlipGenTime() {
		return slipGenTime;
	}

}
