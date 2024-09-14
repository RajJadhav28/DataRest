package com.example.starter.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.starter.models.Employee;
import com.example.starter.repo.EmployeeRepo;

import jakarta.transaction.Transactional;

@Service
public class CustAuthService implements UserDetailsService{
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		return employeeRepo.findByEmail(email)
		.map(employee->{
			
			return new Employee(employee.getEmail(),
					employee.getPassword(),
					employee.getRoles()
					.stream().map(role->new SimpleGrantedAuthority(role))
					.collect(Collectors.toList())
					);
		}).orElseThrow(()-> new UsernameNotFoundException("User with specify email not found"));
		
	}

}
