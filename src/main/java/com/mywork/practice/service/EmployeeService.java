package com.mywork.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywork.practice.dao.EmployeeRepoJDBCTemplate;
import com.mywork.practice.exceptions.RecordNotFoundException;
import com.mywork.practice.model.EmployeeEntity;
import com.mywork.practice.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	EmployeeRepoJDBCTemplate jdbcrepo;
	
	public List<EmployeeEntity> getAllEmplyees(){
		
		List<EmployeeEntity> employees = repository.findAll();//jdbcrepo.findAllEmployees();//
		
		return employees.size() > 0 ? employees : new ArrayList<>();
		
	}
	
	public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {

		Optional<EmployeeEntity> employee = repository.findById(id);//jdbcrepo.findEmployeeById(id);//

		if (employee.isPresent())
			return employee.get();
		else
			throw new RecordNotFoundException("No Record Avaiable for Id - " + id);
	}
	
	public EmployeeEntity createOrUpdateEmployee(EmployeeEntity employee) {
		
		Optional<EmployeeEntity> availableEmployee = repository.findById(employee.getEmployeeId());
		
		if (availableEmployee.isPresent()) {
			EmployeeEntity updateEmployee = availableEmployee.get();
			updateEmployee.setFirstName(employee.getFirstName());
			updateEmployee.setLastName(employee.getLastName());
			updateEmployee.setAddress(employee.getAddress());
			updateEmployee.setEmail(employee.getEmail());
			//updateEmployee.setDepartmentId(employee.getDepartmentId());
			repository.save(updateEmployee);
			return updateEmployee;
		}
		else {
			repository.save(employee);
			return employee;
		}
	}
	
	public void deleteEmployee(Long id) throws RecordNotFoundException {
		
		Optional<EmployeeEntity> employee = repository.findById(id);

		if (employee.isPresent())
			repository.deleteById(id);
		else
			throw new RecordNotFoundException("No Record Avaiable for Id - " + id);

	}
	
	public List<EmployeeEntity> getEmployeeForDepartment(Long department_id) {
		
		List<EmployeeEntity> employees = repository.findByDepartmentId(department_id);
		
		return employees.size() > 0 ? employees : new ArrayList<EmployeeEntity>();
	}
	
}
