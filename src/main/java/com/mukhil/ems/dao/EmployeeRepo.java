package com.mukhil.ems.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mukhil.ems.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
	

}
