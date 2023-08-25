package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Customer;
import com.service.impl.CustomerServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerServiceImpl service;
	
	@PostMapping("/add")
	public void addPerson(@RequestBody Customer customer) {
		service.addToDB(customer);
	}
	
	@RequestMapping("/all")
	public List<Customer> showAll(){
		return service.findAll();
	}
	
	@DeleteMapping("/customer/{id}")
	public void delById(@PathVariable Long id) {
		service.delete(id);
	}
}
