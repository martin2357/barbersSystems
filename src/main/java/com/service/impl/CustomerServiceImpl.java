package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Customer;
import com.repository.CustomerRepository;
import com.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository repository;
	
	public List<Customer> findAll(){
		return repository.findAll();
	}
	
	public void addToDB(Customer customer) {
		repository.saveAndFlush(customer);
		
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	
}
