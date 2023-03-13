package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "departments")
@NoArgsConstructor
@ToString(callSuper = true)
public class Department {
	@Id
	@Column(name="department_id")
//	Created a sequence generator for Department id 
//	where initial value is 4122110, here 4122 is company id, 11 tells that these ids are for dept and 0 is the employee id
	@SequenceGenerator(name = "dept_id_generator",sequenceName = "dept_id_seq",initialValue = 412211000,allocationSize = 1)
	@GeneratedValue(generator = "dept_id_generator")
	private Long departmentId;
	@Column(name = "department_name", length = 50)
	private String departmentName;
	@Column(name = "department_location", length = 50)
	@NotBlank(message = "Department Location is required!!!!")
	private String departmentLocation;
	
	@OneToMany(mappedBy="departmentId",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Employee> employees = new ArrayList<>();

	public Department(String departmentName, String departmentLocation) {
		super();
		this.departmentName = departmentName;
		this.departmentLocation = departmentLocation;
	}
	
	public void addEmployee(Employee emp) {
		employees.add(emp);
		emp.setDepartmentId(this);
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentLocation() {
		return departmentLocation;
	}

	public void setDepartmentLocation(String departmentLocation) {
		this.departmentLocation = departmentLocation;
	}
	
	
}
