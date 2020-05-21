package com.mywork.practice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mywork.practice.model.EmployeeEntity;

@Repository
public interface EmployeeRepoTemplate {
	
	String countQuery = "SELECT COUNT(*) FROM EMPLOYEE";
	String insertQuery = "INSERT INTO Employee (First_Name,Last_Name,Address,Email,Department) VALUES (?,?,?,?,?)";
	String namedInsertQuery = "INSERT INTO Employee (First_Name,Last_Name,Address,Email,Department) VALUES (:firstName,:lastName,:address,:email,:departmentId)";
	String updateQuery = "UPDATE EMPLOYEE SET First_Name = ?, Last_Name = ?, Address = ?, Department = ? WHERE Employee_Id = ?";
	String namedUpdateQuery = "UPDATE EMPLOYEE SET First_Name = :firstName, Last_Name = :lastName, Address = :address, Department = :departmentId WHERE Employee_Id = :employeeId";
	String findAllQuery = "SELECT * FROM EMPLOYEE";
	String findById = "SELECT * FROM EMPLOYEE WHERE Employee_Id = ?";
	String namedfindById = "SELECT * FROM EMPLOYEE WHERE Employee_Id = :employeeId";
	
	public int count();
	
	public int save(EmployeeEntity employee);
	
	public int update(EmployeeEntity employee);
	
	public List<EmployeeEntity> findAllEmployees();
	
	public Optional<EmployeeEntity> findEmployeeById(Long id);
	
}
