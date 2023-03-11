package com.app.repositories;

import static com.app.entities.custom_enum.UserRoles.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.app.entities.UserCredential;
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class TestCredentialRepository {
	
	@Autowired
	private CredentialRepository credRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	void test() {
		assertNotNull(credRepo);
	}
	
	@Test
	void testAddUserCredential() {
		List<UserCredential> credList = List.of(
				new UserCredential("sgadave","swapnil123",valueOf("ROLE_ADMIN")),
				new UserCredential("psaraf","prathamesh123",valueOf("ROLE_ADMIN")),
				new UserCredential("ashetty","abhishek123",valueOf("ROLE_HR")),
				new UserCredential("npatil","nachiket123",valueOf("ROLE_HR")),
				new UserCredential("nkamble","nilesh123",valueOf("ROLE_EMPLOYEE")),
				new UserCredential("chinmayv","chinmay123",valueOf("ROLE_EMPLOYEE")),
				new UserCredential("sdabhade","saurabh123",valueOf("ROLE_APPLICANT")),
				new UserCredential("smali","sumit123",valueOf("ROLE_APPLICANT"))
				);
		
		credList.forEach(cred->cred.setPassword(encoder.encode(cred.getPassword())));
		List<UserCredential> saveAllcred = credRepo.saveAll(credList);
		assertEquals(8,saveAllcred.size());
	}
}
