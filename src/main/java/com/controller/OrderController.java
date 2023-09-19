package com.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.*;
import com.help.*;
import com.help.Number;
import com.model.Customer;
import com.model.Employee;
import com.model.Order;
import com.model.enums.Status;
import com.service.impl.CustomerServiceImpl;
import com.service.impl.EmployeeServiceImpl;
import com.service.impl.OrderServiceImpl;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderServiceImpl orderService;
	
	@Autowired
	EmployeeServiceImpl empService;
	
	@Autowired
	CustomerServiceImpl customerService;
	
	Number numberInst = Number.getInstance();
	
	//Jedna z ciest ako pridavat Action ku konkretnej Order
	 @GetMapping("/{orderId}/actions/{actionId}")
	    public ResponseEntity<String> addActionToOrder(@PathVariable Long orderId, @PathVariable Long actionId) {
	        orderService.addActionToOrder(orderId, actionId);
	        return ResponseEntity.ok("Action added to order.");
	      
	    }
	
	@PostMapping("/add")
	public void createOrder(@RequestBody Order order) throws ParseException {
		String lastNumber= orderService.getLastNumber();
		String newOrderNumber = numberInst.getNewOrderNumber(lastNumber);
		
		Order o = new Order();
				o.setNumber(newOrderNumber);
				o.setEmployeeID(order.getEmployeeID());
				o.setCustomerID(order.getCustomerID());
				o.setStatus(Status.EXPANDED);
				o.setOrderingTime(Helper.getTimeStamp());
				o.setDateExpousure(Helper.getDateFrSql());
				o.addAllAction(order.getActList());
				
				orderService.addToDB(o);
				
				orderService.addListActionsToOrder(o.getActions());
		
		
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> detailOrder(@PathVariable("id") Long id){
		Optional<Order> order = orderService.findById(id);
		OrderDTO o = order.map(this::mapOrderToOrderDTO).orElse(null);
		if(o == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(o);
	}
	
	
	
	@PutMapping("/edit/{id}")
	public void editById(@RequestBody Order order){
		 orderService.update(order);
	}
	
	
	//vyberie všetky akcie patriace k objednakve
	@GetMapping("/{orderId}/actions")
	public ResponseEntity<List<ActionDTO>> getActionsForOrder(@PathVariable Long orderId) {
	    List<ActionDTO> actionDTOs = orderService.getActionsForOrder(orderId);
	    return ResponseEntity.ok(actionDTOs);
	}
	
	// mappers
	
	private OrderDTO mapOrderToOrderDTO(Order order) {
		Long e =(long) order.getEmployeeID();
		Long c =(long) order.getCustomerID();
		Optional<Employee> emp = empService.findById(e);
		List<ActionDTO> actionDTOs = orderService.getActionsForOrder(order.getId());
		Optional<Customer> customer = customerService.findById(c);
		
		return OrderDTO.builder()
				.id(order.getId())
				.number(order.getNumber())
				.employeeID(order.getEmployeeID())
				.employeeName(emp.get().getName())
				.employeeCode(emp.get().getCode())
				.customerCode(customer.get().getCode())
				.customerName(customer.get().getName())
				.actions(actionDTOs)
				.totalPrice(order.getTotalPrice())
				.status(order.getStatus())
				.build();
	}
	
	
	
	
	
	private CustomerViewDTO mapCustomer(Customer customer) {
		return CustomerViewDTO.builder()
				.code(customer.getCode())
				.name(customer.getName())
				.email(customer.getEmail())
				.build();
	}

	private OrderViewDTO mapOrder(Order order) {
		return OrderViewDTO.builder()
				.number(order.getNumber())
				.dateExpousure(order.getDateExpousure())
				.build();
						
	}
	

	
	
	
	
	
	
}
