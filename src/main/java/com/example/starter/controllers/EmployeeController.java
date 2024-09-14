package com.example.starter.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.starter.models.Employee;
import com.example.starter.models.EmployeeRegister;
import com.example.starter.services.EmployeeService;
import com.example.starter.wrapper.ResponseWrapper;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid EmployeeRegister userRequest){
		Employee employee=this.empService.register(userRequest);
		ResponseWrapper responseWrapper=new ResponseWrapper();
		responseWrapper.setMsg("User registered successfully");
		responseWrapper.setData(employee);
		return new ResponseEntity(responseWrapper,HttpStatus.CREATED);
		
	}
	
}
