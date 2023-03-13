package com.app.entities;

import static com.app.entities.custom_enum.UserRoles.valueOf;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.app.entities.custom_enum.UserRoles;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user_credentials")
@ToString(exclude ="userRole")
@NoArgsConstructor
public class UserCredential {
	@Id
	@NotBlank(message = "UserName should be provided")
	private String userName;
	
	@Column(name = "password")
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotBlank(message = "Password should be provided")
	private String password;
	
	@Column(name = "user_role", length = 50)
	@Enumerated(value = EnumType.STRING)
	private UserRoles userRole;
	
	// For pointing username from employees
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToOne(mappedBy = "employeeCredentials", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Employee empId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToOne(mappedBy = "applicantCredentials", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private Applicant appId;
	

	public void setEmployeeId(Employee emp) {
		empId = emp;
		emp.setEmployeeCredentials(this);
	}
	public void setApplicantCredentials(Applicant app) {
		appId = app;
		app.setApplicantCredentials(this);
	}


	public UserCredential(@NotBlank(message = "UserName should be provided") String userName,
			@NotBlank(message = "Password should be provided") String password, UserRoles userRole) {
		super();
		this.userName = userName;
		this.password = password;
		this.userRole = userRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public UserRoles getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = valueOf(userRole);
	}
	public Employee getEmpId() {
		return empId;
	}
	public void setEmpId(Employee empId) {
		this.empId = empId;
	}
	public Applicant getAppId() {
		return appId;
	}
	public void setAppId(Applicant appId) {
		this.appId = appId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
