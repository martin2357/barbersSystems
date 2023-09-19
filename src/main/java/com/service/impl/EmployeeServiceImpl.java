package com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Employee;
import com.model.LoginData;
import com.model.Order;
import com.model.enums.Status;
import com.repository.EmployeeRepository;
import com.service.EmploeeyService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class EmployeeServiceImpl implements EmploeeyService {
	
	@PersistenceContext
    private EntityManager entityManager;

	
	@Autowired
	EmployeeRepository repository;
	
	public List<Employee> findAll(){
		return repository.findAll();
	}
	
	
	private void addToDB(Employee employee) {
		repository.saveAndFlush(employee);
		
		
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@Override
	public Optional<Employee> findById(Long id) {
		return repository.findById(id);
	}

	
	
	public List<Order> findOrderswithV(Long employeeID, Status status){
		return repository.findOrderswithStatus(employeeID, status);
	}

	@Override
	public String register(Employee employee) {
		Boolean isEmailRegistered  = isEmailAlreadyRegistered(employee.getEmail());
		if (isEmailRegistered == false) {
			
			try {
				employee.incrementCode(getLastCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
			addToDB(employee);
	      
			return "Employee saved successfully.";
	    } else if (isEmailRegistered) {
	        return "Email is already registered.";
	    } else {
	        return "Error while checking email registration.";
	    }
		
	}
	
	
	@Override
	public Employee login(LoginData data ) {
		Employee emp =  repository.findByCodeAndPassword(data.getCode(), data.getPassword());
		return emp;
	}
	
	public boolean isEmailAlreadyRegistered(String email) {
		Employee existingEmployee = repository.findByEmail(email);
	     
	    return existingEmployee != null && existingEmployee.getEmail() != null;
	}
	

	@Override
	public String getLastCode() {
		
		return repository.getLastCode();
	}



	
	
	
	
	
	
	
	
	
	
	
}
