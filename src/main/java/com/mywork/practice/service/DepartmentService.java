package com.mywork.practice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywork.practice.model.DepartmentEntity;
import com.mywork.practice.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository repository;
	
	public List<DepartmentEntity> getDepartments(){
		
		List<DepartmentEntity> departments = repository.findAll();
		
		return departments.size() > 0 ? departments : new ArrayList<DepartmentEntity>();
	}
}
