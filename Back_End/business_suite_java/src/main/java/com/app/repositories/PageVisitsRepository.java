package com.app.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.PageVisits;

public interface PageVisitsRepository extends JpaRepository<PageVisits, Long> {
	PageVisits findByDate(LocalDate date);
	Optional<PageVisits> findTopByOrderByIdDesc();
}
