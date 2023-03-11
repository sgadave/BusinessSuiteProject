package com.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Department;
import com.app.entities.Employee;
import com.app.entities.UserCredential;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class TestEmployeeRepository {
	@Autowired 
	private EmployeeRepository empRepo;
	@Autowired
	private CredentialRepository credRepo;
	@Autowired
	private DepartmentRepository deptRepo;

	@Test
	void test() {
		assertNotNull(empRepo);
	}
	
	@Test
	void addAdmins() {
		Employee emp1=new Employee("Swapnil","Kisan","Gadave","Pune",LocalDate.of(2023, 4, 15),"MALE",(long) 1111111111,"AADHAR","Admin",LocalDate.of(2023, 4, 15),5000,50000000,"sgadave1899@gmail.com");
		Employee emp2=new Employee("Prathamesh","Shamrao","Saraf","Pune",LocalDate.of(2023, 4, 15),"MALE",(long) 1111111111,"PASSPORT","Admin",LocalDate.of(2023, 4, 15),5000,50000000,"abcd@gmail.com");
		
		List<UserCredential> credList = credRepo.findAll();
		List<Department> deptList = deptRepo.findAll();
		credList.get(4).setEmployeeId(emp2);
		credList.get(6).setEmployeeId(emp1);
		deptList.get(0).addEmployee(emp2);
		deptList.get(0).addEmployee(emp1);
		
		
		List<Employee> emps = List.of(
				emp1,emp2
				);
		List<Employee> savedAllEmps = empRepo.saveAll(emps);
		assertEquals(2, savedAllEmps.size());
	}
	
	@Test
	void addHREmployees() {
		
		Employee emp2=new Employee("Nachiket","Chandrabhanu","Patil","Pune",LocalDate.of(2023, 4, 15),"MALE",(long) 1111111111,"PANCARD","HR",LocalDate.of(2023, 4, 15),5000,50000000,"abcd@gmail.com");
		Employee emp3= new Employee("Abhishek","Bhaskar","Shetty","Pune",LocalDate.of(2023, 4, 15),"MALE",(long) 1111111111,"DRIVINGLICENSE","HR",LocalDate.of(2023, 4, 15),5000,50000000,"abcd@gmail.com");
		
		List<UserCredential> credList = credRepo.findAll();
		List<Department> deptList = deptRepo.findAll();
		credList.get(0).setEmployeeId(emp3);
		credList.get(3).setEmployeeId(emp2);
		deptList.get(1).addEmployee(emp3);
		deptList.get(2).addEmployee(emp2);
		
		
		List<Employee> emps = List.of(
				emp2,emp3
				);
		List<Employee> savedAllEmps = empRepo.saveAll(emps);
		assertEquals(2, savedAllEmps.size());
	}
	
	@Test
	void addEmployees() {
		Employee emp1=new Employee("Nilesh","Bajirao","Kamble","Pune",LocalDate.of(2023, 4, 15),"MALE",(long) 1111111111,"PASSPORT","HR",LocalDate.of(2023, 4, 15),5000,50000000,"abcd@gmail.com");
		Employee emp2=new Employee("Chinmay","Satish","Vaywhare","Pune",LocalDate.of(2023, 4, 15),"MALE",(long) 1111111111,"AADHAR","Accounts",LocalDate.of(2023, 4, 15),5000,50000000,"abcd@gmail.com");
		
		List<UserCredential> credList = credRepo.findAll();
		List<Department> deptList = deptRepo.findAll();
		credList.get(1).setEmployeeId(emp2);
		credList.get(2).setEmployeeId(emp1);
		deptList.get(2).addEmployee(emp2);
		deptList.get(1).addEmployee(emp1);
		
		List<Employee> emps = List.of(
				emp1,emp2
				);
		List<Employee> savedAllEmps = empRepo.saveAll(emps);
		assertEquals(2, savedAllEmps.size());
	}

}
