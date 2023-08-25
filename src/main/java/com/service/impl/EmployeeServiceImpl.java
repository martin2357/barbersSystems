package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Employee;
import com.repository.EmployeeRepository;
import com.service.EmploeeyService;

@Service
public class EmployeeServiceImpl implements EmploeeyService {

	@Autowired
	EmployeeRepository repository;
	
	public List<Employee> findAll(){
		return repository.findAll();
	}
	public void addToDB(Employee customer) {
		repository.saveAndFlush(customer);
		
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	
}
