package com.example.starter.services;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.starter.models.Employee;
import com.example.starter.models.EmployeeRegister;
import com.example.starter.repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Employee register(EmployeeRegister userRequest) {
		
		if(this.employeeRepo.findByEmail(userRequest.getEmail()).isPresent()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User with emailID request already exist");
		}
		
		Employee employee=new Employee();
		employee.setName(userRequest.getName());
		employee.setEmail(userRequest.getEmail());
		employee.setPassword(passwordEncoder.encode(userRequest.getPassword()));
//		employee.setRoles(Arrays.asList("USER"));
//		second type to see if user exist in the database
		if(userRequest.getRoles().isEmpty()) {
			employee.setRoles(Arrays.asList("USER"));
		}else {
			employee.setRoles(userRequest.getRoles());
		}
		//System.out.println(employee);
		
		return employeeRepo.save(employee);
	}
	

}
