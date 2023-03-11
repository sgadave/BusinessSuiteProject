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

import com.app.entities.Applicant;
import com.app.entities.UserCredential;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class TestApplicantRepository {
	@Autowired 
	private ApplicantRepository appRepo;
	@Autowired
	private CredentialRepository credRepo;

	@Test
	void test() {
		assertNotNull(appRepo);
	}
	
	@Test
	void addApplicants() {
		Applicant app1=new Applicant("Saurabh","Rajesh","Dabhade","Pune",LocalDate.of(2023, 4, 15),"MALE",(long) 1111111111,"AADHAR",(long) 123456789,"sdabhade@gmail.com","1122334455","1122334455","B.E.",80.66,LocalDate.of(2023, 4, 15),LocalDate.of(2023, 4, 15),85.66,25);
		Applicant app2=new Applicant("Sumit","Hello","Mali","Pune",LocalDate.of(2023, 4, 15),"MALE",(long) 1111111111,"AADHAR",(long) 123456789,"sdabhade@gmail.com","1122334455","1122334455","B.E.",80.66,LocalDate.of(2023, 4, 15),LocalDate.of(2023, 4, 15),85.66,25);
		
		List<UserCredential> credList = credRepo.findAll();
		credList.get(7).setApplicantCredentials(app2);
		credList.get(5).setApplicantCredentials(app1);
		
		
		List<Applicant> appList = List.of(
				app1,app2
				);
		List<Applicant> savedAllEmps = appRepo.saveAll(appList);
		assertEquals(2, savedAllEmps.size());
	}
	
	

}
