package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CustomerDTO;
import com.dto.CustomerViewDTO;
import com.dto.OrderViewDTO;
import com.model.*;
import com.service.impl.CustomerServiceImpl;
import com.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerServiceImpl service;
	
	@Autowired
	OrderServiceImpl orderService;
		
	
	@PostMapping("/add")
	public ResponseEntity<String> addPerson(@RequestBody Customer customer) {
	    String resultMessage = service.register(customer);
	    
	    if (resultMessage.equals("Customer saved successfully.")) {
	        return ResponseEntity.status(HttpStatus.CREATED).body(resultMessage);
	    } else if (resultMessage.equals("Email is already registered.")) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMessage);
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMessage);
	    }
	}
	
	@RequestMapping("/all")
	public List<CustomerDTO> showAll(){
	List<Customer>customerList = service.findAll();
	List<CustomerDTO> customerDTOList = new ArrayList<CustomerDTO>();
	
	if(customerList != null) {
	
		for (Customer c : customerList) {
			CustomerDTO cDTO = Optional.ofNullable(c)
		            .map(customer -> mapCustomerToCustomerDTO(customer))
		            .orElse(null);
		    customerDTOList.add(cDTO);
		}
	}
		 return customerDTOList;
	}
	
	@DeleteMapping("/del/{id}")
	public void delById(@PathVariable Long id) {
		service.delete(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> detailCustomer(@PathVariable("id") Long id){
		Optional<Customer> customer = service.findById(id);
		CustomerDTO p = customer.map(this::mapCustomerToCustomerDTO).orElse(null);
		 if (p == null) {
		      return ResponseEntity.notFound().build();
		    }
		    return ResponseEntity.ok(p);
		
	} 
	
	
	//mappers

	
	private CustomerDTO mapCustomerToCustomerDTO(Customer customer) {
	List<OrderViewDTO> orders =
			customer.getOrders().stream().map(this::mapOrder).collect(Collectors.toList());
		
		return CustomerDTO.builder()
				.id(customer.getId())
				.code(customer.getCode())
				.name(customer.getName())
				.email(customer.getEmail())
				.orders(orders)
				.build();
	}
	
	
	
	
	private OrderViewDTO mapOrder(Order order) {
		return OrderViewDTO.builder()
				.number(order.getNumber())
				.dateExpousure(order.getDateExpousure())
				.price(order.getTotalPrice())
				.status(order.getStatus())
				.build();
						
	}
	
	
	
	
	
	
	
	
}
