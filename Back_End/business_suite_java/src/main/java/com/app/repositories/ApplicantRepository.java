package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
	Optional<Applicant> findByApplicantCredentialsUserName(String userName);
}
