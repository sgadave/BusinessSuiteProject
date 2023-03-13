package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.EmployeePerformance;

public interface EmployeePerformanceRepository extends JpaRepository<EmployeePerformance, Long> {
}
