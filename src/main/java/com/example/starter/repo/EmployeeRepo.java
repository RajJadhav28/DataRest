package com.example.starter.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.starter.models.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Integer>{

	public Optional<Employee> findByEmail(String email);

}
