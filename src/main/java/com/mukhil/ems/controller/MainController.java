package com.mukhil.ems.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mukhil.ems.dao.EmployeeRepo;
import com.mukhil.ems.exception.ResourceNotFoundException;
import com.mukhil.ems.model.Employee;

@RestController
@RequestMapping("/api/v1")
public class MainController {
	
	@Autowired
	EmployeeRepo repo;
	
	@GetMapping("/home")
	public String getHome() {
		return "home";
	}
	
	@CrossOrigin(origins="https://angular-ems-crud.herokuapp.com")
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id) {
		Employee employee=repo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee with "+id+" doesn't exist"));
		return ResponseEntity.ok(employee);
	}
	
	
	
	@CrossOrigin(origins="https://angular-ems-crud.herokuapp.com")
	@GetMapping("/getEmployees")
	public List<Employee> getEmployees(){
		return repo.findAll();
	}
	
	@CrossOrigin(origins="https://angular-ems-crud.herokuapp.com")
	@PostMapping("/addEmployee")
	public String addEmployee(@RequestBody Employee employee) {
		repo.save(employee);
		return "Added: Employee with ID"+employee.getId();
		
	}
	
	@CrossOrigin(origins="https://angular-ems-crud.herokuapp.com")
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id,@RequestBody Employee employeeDetails) {
		
		Employee employee=repo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee with "+id+" doesn't exist"));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employee updatedEmployee=repo.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@CrossOrigin(origins="https://angular-ems-crud.herokuapp.com")
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable("id") int id) {
		
		Employee employee=repo.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Employee with "+id+" doesn't exist"));
		repo.deleteById(id);
		Map<String,Boolean> response=new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	

}
