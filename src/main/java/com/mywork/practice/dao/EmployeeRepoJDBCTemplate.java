package com.mywork.practice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mywork.practice.model.EmployeeEntity;

@Repository
public class EmployeeRepoJDBCTemplate implements EmployeeRepoTemplate {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	NamedParameterJdbcTemplate namedTemplate;
	
	@Override
	public int count() {
		return jdbcTemplate.queryForObject(countQuery, Integer.class);
	}

	@Override
	public int save(EmployeeEntity employee) {
		/*
		 * return jdbcTemplate.update(insertQuery, employee.getFirstName(),
		 * employee.getLastName(), employee.getAddress(), employee.getEmail(),
		 * employee.getDepartmentId());
		 */
		return namedTemplate.update(namedInsertQuery, new BeanPropertySqlParameterSource(employee));
	}

	@Override
	public int update(EmployeeEntity employee) {
		return jdbcTemplate.update(updateQuery,employee.getFirstName(), employee.getLastName(), employee.getAddress(),
				employee.getEmail(), /* employee.getDepartmentId(), */ employee.getEmployeeId());
	}

	@Override
	public List<EmployeeEntity> findAllEmployees() {
		return jdbcTemplate.query(findAllQuery,
				(rs, rowNum) -> new EmployeeEntity(rs.getLong("Employee_Id"), rs.getString("First_Name"),
						rs.getString("Last_Name"), rs.getString("Address"), rs.getString("Email"),
						rs.getLong("Department")));
	}

	@Override
	public Optional<EmployeeEntity> findEmployeeById(Long id) {
		/*
		 * return jdbcTemplate.queryForObject(findById, new Object[] { id }, (rs,
		 * rowNum) -> Optional.of(new EmployeeEntity(rs.getLong("Employee_Id"),
		 * rs.getString("First_Name"), rs.getString("Last_Name"),
		 * rs.getString("Address"), rs.getString("Email"), rs.getLong("Department"))));
		 */
		return namedTemplate.queryForObject(namedfindById, new MapSqlParameterSource("employeeId", id),
				(rs, rowNum) -> Optional.of(new EmployeeEntity(rs.getLong("Employee_Id"), rs.getString("First_Name"),
						rs.getString("Last_Name"), rs.getString("Address"), rs.getString("Email"),
						rs.getLong("Department"))));
	}

}
