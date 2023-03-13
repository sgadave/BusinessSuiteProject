package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.PaySlip;

public interface PaySlipRepository extends JpaRepository<PaySlip, Long>  {
	List<PaySlip> findAllByEmpId(Long empId);
}
