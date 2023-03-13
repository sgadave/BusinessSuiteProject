package com.app.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class TestDepartmentRepository {

	@Autowired
	private DepartmentRepository deptRepo;
	@Test
	void testAddDepartments() {
		List<Department> deptList = List.of(
				new Department("Admin","Pune"),
				new Department("HR","Pune"),
				new Department("Accounts","Pune")
				);
		List<Department> saveAllDept = deptRepo.saveAll(deptList);
		assertEquals(3,saveAllDept.size());
	}

}
