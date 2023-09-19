package com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Customer;
import com.model.Employee;
import com.model.LoginData;
import com.repository.CustomerRepository;
import com.service.CustomerService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	public List<Customer> findAll(){
		return repository.findAll();
	}
	
	private void addToDB(Customer customer) {
		repository.saveAndFlush(customer);
		
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public String register(Customer customer) {
		Boolean isEmailRegistered  = isEmailAlreadyRegistered(customer.getEmail());
	if (isEmailRegistered == false) {
			
			try {
				customer.incrementCode(getLastCode());
			} catch (Exception e) {
				e.printStackTrace();
			}
			addToDB(customer);
	      
			return "Customer saved successfully.";
	    } else if (isEmailRegistered) {
	        return "Email is already registered.";
	    } else {
	        return "Error while checking email registration.";
	    }
		
	}

	@Override
	public String getLastCode() {
		return repository.getLastCode();
	}

	@Override
	public Customer login(LoginData data) {
		Customer cust = repository.findByCodeAndPassword(data.getCode(), data.getPassword());
		return cust;
	}

	public boolean isEmailAlreadyRegistered(String email) {
		Customer existingCustomer = repository.findByEmail(email);
		return existingCustomer != null && existingCustomer.getEmail() != null;
	}
	

	
}
