package com.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.model.Customer;
import com.model.Employee;
import com.repository.CustomerRepository;
import com.repository.EmployeeRepository;
import com.service.CustomerService;

@Component
public class DataInit implements CommandLineRunner{

	private final CustomerRepository customerRepo;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	
	
	
	public DataInit(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}



	@Override
	public void run(String... args) throws Exception {
	/*	Customer c = new Customer(null, "1081", "Fero", "Ferkovic", "@", "33698");
		Customer c2 = new Customer(null, "1082", "Jano", "Uloca", "ulica@gmail", "33698");	
		customerRepo.saveAndFlush(c);
		customerRepo.saveAndFlush(c2);
		*/
		
		Employee e = new Employee(null,"0001", "Laco", "Malina","xpv@hotmail.com","3213516");
		Employee e2 = new Employee(null,"0002", "Robo", "Grig","abc@hotmail.com","6669936");
		
		employeeRepo.saveAndFlush(e);
		employeeRepo.saveAndFlush(e2);
	}
	
	
}
