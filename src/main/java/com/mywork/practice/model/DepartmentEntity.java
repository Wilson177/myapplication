package com.mywork.practice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Department")
public class DepartmentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Department_ID")
	private Long id;
	
	@Column(name = "Department_Name")
	private String name;
	
	/*
	 * @Column(name = "Business_Org") private int businessOrgId;
	 */
	
	@JsonIgnoreProperties("department")
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EmployeeEntity> employees = new HashSet<EmployeeEntity>();
	
	@JsonIgnoreProperties("departments")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	/*
	 * @JoinTable(name = "Business_Org", joinColumns = @JoinColumn(name =
	 * "Business_Org"), inverseJoinColumns = @JoinColumn(name = "Business_Org_Id"))
	 */
	@JoinColumn(name = "Business_Org")
	private BusinessOrg businessOrg;

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public int getBusinessOrgId() { return businessOrgId; }
	 * 
	 * public void setBusinessOrgId(int businessOrgId) { this.businessOrgId =
	 * businessOrgId; }
	 */

	public Set<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public BusinessOrg getBusinessOrg() {
		return businessOrg;
	}

	public void setBusinessOrg(BusinessOrg businessOrg) {
		this.businessOrg = businessOrg;
	}
	
}
