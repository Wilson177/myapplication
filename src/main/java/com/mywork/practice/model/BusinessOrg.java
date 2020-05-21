package com.mywork.practice.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Business_Org")
public class BusinessOrg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Business_Org_Id")
	private Long business_org_Id;
	
	@Column(name = "Description")
	private String description;
	
	@JsonIgnoreProperties("businessOrg")
	@OneToMany(mappedBy = "businessOrg", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DepartmentEntity> departments;
	
	
	public Set<DepartmentEntity> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<DepartmentEntity> departments) {
		this.departments = departments;
	}

	public Long getId() {
		return business_org_Id;
	}

	public void setId(Long id) {
		this.business_org_Id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
