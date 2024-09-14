package com.example.starter.models;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeRegister {
	//validations
	private String name;
	
	@Email
	@NotNull
	private String email;
	
	@Size(min=6,max=20)
	private String password;
	
	private List<String> roles;
	
	public EmployeeRegister() {
		
	}

	public EmployeeRegister(String name, @Email @NotNull String email, @Size(min = 6, max = 20) String password,
			List<String> roles) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	

}
