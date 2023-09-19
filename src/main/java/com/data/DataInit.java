package com.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.model.*;
import com.model.enums.Status;
import com.repository.ActionRepository;
import com.repository.CustomerRepository;
import com.repository.EmployeeRepository;
import com.service.impl.OrderServiceImpl;
import com.service.impl.TraceOrderActionServImpl;
import com.help.Helper;
import com.help.Number;
@Component
public class DataInit implements CommandLineRunner{

	private final CustomerRepository customerRepo;
	
	@Autowired
	TraceOrderActionServImpl traceService;
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	ActionRepository actionRepository;
	
	@Autowired
	OrderServiceImpl orderService;
	
	
	
	public DataInit(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}



	@Override
	public void run(String... args) throws Exception {
	
		//## Plnenie DB Customers and Empls
		/*
		Customer c = new Customer(null, "1081", "Fero", "Ferkovic", "@", "33698");
		Customer c2 = new Customer(null, "1082", "Jano", "Uloca", "ulica@gmail", "33698");	
		customerRepo.saveAndFlush(c);
		customerRepo.saveAndFlush(c2);
		*/
		
		
		//Employee e = new Employee(null,"0003", "Fero", "Rosko","ferorosko@hotmail.com","3213516");
		//Employee e2 = new Employee(null,"0004", "Mária", "Vesela","mariavesela@hotmail.com","6669936");
		//Failing row contains (4, null, null, null, mariavesela@hotmail.com, null).
		
		
		
		/*
		Employee e3 = new Employee();
		e3.setId(null);
		e3.setCode("0003");
		e3.setEmail("mariavesela@hotmail.com");
		e3.setLastName("Vesela");
		e3.setName("Mária");
		e3.setOrders(null);
		e3.setPassword("123456987");
		
		employeeRepo.saveAndFlush(e3);
		*/

	
		
		//# Plnennie db Actions
		/*
		Action a = new Action(null, "001", "Hair cutting", 15.21);
		Action b = new Action(null, "002", "Beard haircut", 10.11);
		Action c = new Action(null, "003", "Styling", 8.60);
		Action d = new Action(null, "004", "kids cutting", 9.99);
		Action e = new Action(null, "005", "Washing", 6.30);
		Action f = new Action(null, "006", "Consultation", 9.20);
		
		actionRepository.saveAndFlush(a);
		actionRepository.saveAndFlush(b);
		actionRepository.saveAndFlush(c);
		actionRepository.saveAndFlush(d);
		actionRepository.saveAndFlush(e);
		actionRepository.saveAndFlush(f);
	

		
		
		
	
	
		
		
		
		//### Tvorba Order - tato cesta uz ale nefunguje cez aktualny konstruktor
		
		/*
		Action a = actionRepository.findById(9L).orElseThrow(null);
		Action b = actionRepository.findById(10L).orElseThrow(null);
		Action c = actionRepository.findById(11L).orElseThrow(null);
		Number numberInst = Number.getInstance();
		
		String lastNumber= orderService.getLastNumber();
		String newNumber = numberInst.getNewOrderNumber(lastNumber);
		
		// convert list action to TraceListAction
		List<Action> xaction = new ArrayList<Action>();
		xaction.add(a);
		xaction.add(b);
		xaction.add(c);
		
		
		Order createOrder = new Order();
		createOrder.setNumber(newNumber);
		createOrder.setEmployeeID(11);
		createOrder.setCustomerID(11);
		createOrder.setStatus(Status.EXPANDED);
		createOrder.setOrderingTime(Helper.getTimeStamp());
		createOrder.setDateExpousure(Helper.getDateFrSql());
		createOrder.addAllAction(xaction);
		
		orderService.addToDB(createOrder);
		
		
		orderService.addListActionsToOrder(createOrder.getActions());
		*/
		
	}
	
	
}
