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

import com.dto.EmployeeDTO;
import com.dto.OrderViewDTO;
import com.model.Employee;
import com.model.LoginData;
import com.model.Order;
import com.model.enums.Status;
import com.service.impl.CustomerServiceImpl;
import com.service.impl.EmployeeServiceImpl;
import com.service.impl.OrderServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;


@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	HttpServletRequest request;
	
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	CustomerServiceImpl service;
	
	@Autowired
	OrderServiceImpl orderService;
	
	@Autowired
	EmployeeServiceImpl empService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addPerson(@RequestBody Employee employee) {
	    String resultMessage = empService.register(employee);
	    
	    if (resultMessage.equals("Employee saved successfully.")) {
	        return ResponseEntity.status(HttpStatus.CREATED).body(resultMessage);
	    } else if (resultMessage.equals("Email is already registered.")) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMessage);
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultMessage);
	    }
	}
	
	

	// pouzivat az pri spravenom Front-end
	@PostMapping("/login")
	public ResponseEntity<String> tryToLogin(@RequestBody LoginData data){
		
	//	Optional<Employee> emp = Optional.ofNullable(empService.login(data));
		Employee emp= empService.login(data);
		
		if(emp != null) {
			 EmployeeDTO empDTO = mapEmpToEmpDTO(emp, null);
			  httpSession.setAttribute("empDTO", empDTO);
			
			  return ResponseEntity.ok("Login successful "); 
			
			  // pristup k session objektu
			  //EmployeeDTO storedEmployeeDTO = (EmployeeDTO) httpSession.getAttribute("employeeDTO");
			  
			  //zmazanie zo session
			 // httpSession.removeAttribute("employeeDTO");
			
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Login failed: Incorrect username or password");
		}
		
	}
	
		@RequestMapping("/all")
		public List<EmployeeDTO> showAll(){
		List<Employee>empList = empService.findAll();
		List<EmployeeDTO> empDTOList = new ArrayList<EmployeeDTO>();
		
		if(empList != null) {
		
			for (Employee emp : empList) {
			    EmployeeDTO e = Optional.ofNullable(emp)
			            .map(employee -> mapEmpToEmpDTO(employee, null))
			            .orElse(null);
			    empDTOList.add(e);
			}

		}
			 
			 return empDTOList;
		}
		
		
		@DeleteMapping("/del/{id}")
		public void delById(@PathVariable Long id) {
			empService.delete(id);
		}
		
	
	
		@RequestMapping("/{employeeID}/{status}")
		public ResponseEntity<EmployeeDTO> getSortedDaiolyOrdersForBarber(@PathVariable("employeeID") Long employeeID,@PathVariable Character status){
			Optional<Employee> emp = empService.findById(employeeID);
			List<Order> orders = findDailyOrders(employeeID, status);
			List<OrderViewDTO> ordersDTO = mapOrders(orders);
			
			EmployeeDTO e = emp.map(employee -> mapEmpToEmpDTO(employee, ordersDTO)).orElse(null);
			
			if(e == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(e);
			
		}
		
		
		@GetMapping("/{id}")
		public ResponseEntity<EmployeeDTO>detailEmployee(@PathVariable("id") Long id){
			Optional<Employee> emp = empService.findById(id);
			EmployeeDTO e = emp.map(employee -> mapEmpToEmpDTO(employee, null)).orElse(null);
			
			if(e == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(e);
			
		}
		
		
		public List<Order>findDailyOrders(Long employeeID, char status){
			Status orderStatus = Status.getOrderStatusFromDatabaseValue(status);
			return empService.findOrderswithV(employeeID, orderStatus);
		}
		
		
		private List<OrderViewDTO> mapOrders(List<Order> orders) {
		    return orders.stream()
		        .map(this::mapOrder)
		        .collect(Collectors.toList());
		}
		
		
		private OrderViewDTO mapOrder(Order order) {
			return OrderViewDTO.builder()
					.number(order.getNumber())
					.dateExpousure(order.getDateExpousure())
					.price(order.getTotalPrice())
					.status(order.getStatus())
					.build();
							
		}
		
		private EmployeeDTO mapEmpToEmpDTO(Employee emp,List<OrderViewDTO>ordersDTO) {
			List<OrderViewDTO> ordersDTOx= null;
			
			if(ordersDTO == null) {
				ordersDTOx=	emp.getOrders().stream().map(this::mapOrder).collect(Collectors.toList());
			}
			else { ordersDTOx = ordersDTO; 	}
							
					
			return EmployeeDTO.builder()
					.code(emp.getCode())
					.name(emp.getName())
					.lastName(emp.getLastName())
					.email(emp.getEmail())
					.orders(ordersDTOx)
					.build();
		}
		
		
	
		
		
		
		
		
		
		
		
		
}
