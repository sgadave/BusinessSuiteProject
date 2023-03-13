package com.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
	List<Interview> findAllByApplicantIdApplicantId(Long id);
	List<Interview> findAllByInterviewerIdEmployeeId(Long id);
	Optional<Interview> findByInterviewerIdEmployeeId(Long id);
}
