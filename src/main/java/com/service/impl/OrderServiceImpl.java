package com.service.impl;

import com.exception.*;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.ActionDTO;
import com.exception.ActionNotFoundException;
import com.model.*;
import com.model.TraceOrderAction;
import com.repository.ActionRepository;
import com.repository.OrderRepository;
import com.service.OrderService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@PersistenceContext
    private EntityManager entityManager;

	
		private final OrderRepository orderRepository;
	    private final ActionRepository actionRepository;
	    private final TraceOrderActionServImpl traceService;
	  

	    
	    
	public OrderServiceImpl(OrderRepository orderRepository, ActionRepository actionRepository,
				TraceOrderActionServImpl traceService) {
			super();
			this.orderRepository = orderRepository;
			this.actionRepository = actionRepository;
			this.traceService = traceService;
		}
	
	
	//Add action to order
	public void addActionToOrder(Long orderId, Long actionId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        Action action = actionRepository.findById(actionId)
                .orElseThrow(() -> new ActionNotFoundException(actionId));

        traceService.logAction(order, action);
    }
	
	
	//Add all action to order
		public void addAllActionToOrder(Long orderId, List<TraceOrderAction> list) {
	        Order order = orderRepository.findById(orderId)
	                .orElseThrow(() -> new OrderNotFoundException(orderId));
	        
	        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Action actionX = (Action) iterator.next();
				 traceService.logAction(order, actionX);
			}
	        
	    }


	public List<Order> findAll(){
		return orderRepository.findAll();
	}
	
	
	public Order addToDB(Order order) {
		 return orderRepository.saveAndFlush(order);
		
	}
	
	public Order update(Order order) {
		return orderRepository.saveAndFlush(order);
	}
	
	
	public Optional<Order> findById(Long id) {
		
		 return orderRepository.findById(id);
	}
	
	
	public Long findLastEntity() {
		
		// Define a JPQL query to retrieve the last entity
        String sql = "SELECT MAX(id) FROM t_orders"; 
        
     // Create a native SQL query
        Query query = entityManager.createNativeQuery(sql);
        
     // Execute the query and get the result
        Object result = query.getSingleResult();
        
        Long maxId = ((Number) result).longValue();
        
        return maxId;
      
        
        
       
	}
	
	public List<Order>findOrderswithV(){
		return orderRepository.findOrderswithV();
	}
	
	public String getLastNumber() {
		
		Long lastId = findLastEntity();
		Order lastOrder = findById(lastId).orElse(null);
		
		return lastOrder.getNumber();
		
	}
	
	
	@Transactional
	public List<ActionDTO> getActionsForOrder(Long orderId) {
	    Optional<Order> orderOptional = orderRepository.findById(orderId);
	    
	    if (orderOptional.isEmpty()) {
	        throw new OrderNotFoundException(orderId);
	    }
	    
	    Order order = orderOptional.get();
	    
	    List<TraceOrderAction> actions = order.getActions();
	    
	    List<ActionDTO> actionDTOs = actions.stream()
	        .map(action -> new ActionDTO(action.getActionName(), action.getActionMinuteRate()))
	        .collect(Collectors.toList());
	    
	    return actionDTOs;
	}



	 public void addActionToOrder(Long orderId, Action action) {
	        Optional<Order> optionalOrder = orderRepository.findById(orderId);
	        if (optionalOrder.isPresent()) {
	            Order order = optionalOrder.get();
	            order.addAction(action);
	            orderRepository.save(order);
	        } else {
	        	 throw new com.exception.OrderNotFoundException(("Objednávka s ID " + orderId + " neexistuje."));
	        }
	    }


	public void addListActionsToOrder(List<TraceOrderAction> actions) {
		for (Iterator iterator = actions.iterator(); iterator.hasNext();) {
			TraceOrderAction traceOrderAction = (TraceOrderAction) iterator.next();
			
			traceService.save(traceOrderAction);
		}
	}
	
}
