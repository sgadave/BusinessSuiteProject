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

import com.app.entities.JobDescription;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class TestJobDescriptionRepository {
	@Autowired
	private JobDescriptionRepository jDRepo;

	@Test
	void test() {
		assertNotNull(jDRepo);
	}

	@Test
	void addJobDescriptions() {

		JobDescription jd1 = new JobDescription("Summer Recruitment", "SYSTEM_ENGINEER", "FULLTIME",
				LocalDate.of(2023, 2, 26), LocalDate.of(2023, 2, 26), LocalDate.of(2023, 5, 26), 5000000.00, "PUNE",
				"B.E.", "Hello,SystemEngineer, Engineer", "ACTIVE", "HELLO");
		JobDescription jd2 = new JobDescription("Winter Recruitment", "QA_ANALYST", "PARTTIME",
				LocalDate.of(2023, 2, 26), LocalDate.of(2023, 2, 26), LocalDate.of(2023, 5, 26), 5000000.00, "MUMBAI",
				"B.E.", "QA Analyst,Manager,", "ACTIVE", "HELLO");

		List<JobDescription> jDList = List.of(jd1, jd2);
		List<JobDescription> savedAllEmps = jDRepo.saveAll(jDList);
		assertEquals(2, savedAllEmps.size());
	}

}
