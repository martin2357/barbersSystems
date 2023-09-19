package com.service;

import java.util.Optional;

import com.model.Employee;
import com.model.LoginData;

public interface EmploeeyService {
	
	Optional<Employee> findById(Long id);
	
	String register(Employee emp);
	
	String getLastCode();

	Employee login(LoginData data);
	
	
	
	
	
}
