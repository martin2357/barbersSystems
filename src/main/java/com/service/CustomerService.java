package com.service;

import java.util.Optional;

import com.model.Customer;
import com.model.LoginData;
public interface CustomerService {

	Optional<Customer> findById(Long id);
	
	String register(Customer customer);
	
	String getLastCode();

	Customer login(LoginData data);
}
