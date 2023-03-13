package com.app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.Department;
import com.app.repositories.DepartmentRepository;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository deptRepo;
	@Override
	public List<Department> getAllEmployeeDetails() {
		// TODO Auto-generated method stub
		return deptRepo.findAll();
	}

}
