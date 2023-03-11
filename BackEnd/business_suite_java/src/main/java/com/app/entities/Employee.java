package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.app.entities.custom_enum.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
@ToString(callSuper = true)
public class Employee extends Person {
	@Id
	@Column(name = "employee_id")
//	Created a sequence generator for employee id 
//	where initial value is 4122180, here 4122 is company id, 18 tells that these ids are for employees and 0 is the employee id
	@SequenceGenerator(name = "emp_id_generator", sequenceName = "emp_id_seq", initialValue = 412218000, allocationSize = 1)
	@GeneratedValue(generator = "emp_id_generator")
	private Long employeeId;
	@Column(length = 50, name = "designation")
	@NotBlank(message = "Designation is required!!!!")
	private String designation;
	@Column(name = "email_id", length = 50)
	private String emailId;
	@OneToOne
	@JoinColumn(name = "department_id")
	private Department departmentId;
	@Column(name = "joining_date")
	private LocalDate joiningDate;
	private double commission;
	@Column(name = "net_salary")
	private double netSalary;
	@OneToOne
	@JoinColumn(name = "user_name")
	private UserCredential employeeCredentials;
//	@OneToOne
//	@JoinColumn(name = "manager_id")
//	private Employee manager;
//	@JsonProperty(access = Access.WRITE_ONLY)
//	@OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
//	private List<Employee> employees = new ArrayList<>();
	
	@JsonProperty(access=Access.WRITE_ONLY)
	@OneToMany(mappedBy="employeePerformance", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmployeePerformance> performance = new ArrayList<>();

	@JsonProperty(access=Access.WRITE_ONLY)
	@OneToMany(mappedBy = "interviewerId",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<Interview> interviewSchedule = new ArrayList<Interview>();
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "empId",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	private List<PaySlip> salarySlip = new ArrayList<PaySlip>();
	
	
	public void addSalarySlip(PaySlip pay) {
		salarySlip.add(pay);
		pay.setEmpId(this);
	}
	
	
	public void addInterview(Interview interview) {
		interviewSchedule.add(interview);
		interview.setInterviewerId(this);
	}
	
//	
//	public void addEmployee(Employee emp) {
//		employees.add(emp);
//		emp.setManager(this);
//	}
//	
	public void addEmployeePerformance(EmployeePerformance emp) {
		performance.add(emp);
		emp.setEmployeePerformance(this);
	}

	public Employee(String firstName, String middleName, String lastName, String address, LocalDate dob, String gender,
			Long governmentIdNumber, String govIdType, @NotBlank(message = "Designation is required!!!!") String designation,
			@NotBlank(message = "Joining Date is required!!!!") LocalDate joiningDate, double commission,
			double netSalary,String emailId) {
		super(firstName, middleName, lastName, address, dob, Gender.valueOf(gender),governmentIdNumber, govIdType);
		this.designation = designation;
		this.joiningDate = joiningDate;
		this.commission = commission;
		this.netSalary = netSalary;
		this.emailId=emailId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Department getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(double netSalary) {
		this.netSalary = netSalary;
	}

	public UserCredential getEmployeeCredentials() {
		return employeeCredentials;
	}

	public void setEmployeeCredentials(UserCredential employeeCredentials) {
		this.employeeCredentials = employeeCredentials;
	}

//	public Employee getManager() {
//		return manager;
//	}
//
//	public void setManager(Employee manager) {
//		this.manager = manager;
//	}
//
//	public List<Employee> getEmployees() {
//		return employees;
//	}
//
//	public void setEmployees(List<Employee> employees) {
//		this.employees = employees;
//	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
