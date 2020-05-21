package com.mywork.practice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mywork.practice.exceptions.RecordNotFoundException;
import com.mywork.practice.model.DepartmentEntity;
import com.mywork.practice.model.EmployeeEntity;
import com.mywork.practice.service.DepartmentService;
import com.mywork.practice.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {
	
	@Autowired
	EmployeeService service;
	
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees(){
		
		List<EmployeeEntity> employees = service.getAllEmplyees();
		
		return new ResponseEntity<List<EmployeeEntity>>(employees, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException {
		
		EmployeeEntity employee = service.getEmployeeById(id);
		
		return new ResponseEntity<EmployeeEntity>(employee, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<EmployeeEntity> createUpdateEmployee(@RequestBody EmployeeEntity employee) {
		
		EmployeeEntity createdUpdatedEmployee = service.createOrUpdateEmployee(employee);
		
		return new ResponseEntity<EmployeeEntity>(createdUpdatedEmployee, new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@GetMapping("/department/{id}")
	public ResponseEntity<List<EmployeeEntity>> getEmployeesByDepartment(@PathVariable("id") Long department_id){
		
		List<EmployeeEntity> employees = service.getEmployeeForDepartment(department_id);
		
		return new ResponseEntity<List<EmployeeEntity>>(employees, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/department/departments")
	public ResponseEntity<List<DepartmentEntity>> getDepartments(){
		
		List<DepartmentEntity> departments = departmentService.getDepartments();
		
		return new ResponseEntity<>(departments, new HttpHeaders(), HttpStatus.OK);
	}
}
