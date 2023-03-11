package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.JobDescription;
import com.app.entities.custom_enum.JobLocation;

public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long> {
	List<JobDescription> findAllByCity(JobLocation value);
	
	@Query("SELECT j FROM JobDescription j WHERE j.keywords LIKE %:key%")
	List<JobDescription> findAllByKeywords(@Param("key")  String value);
}
