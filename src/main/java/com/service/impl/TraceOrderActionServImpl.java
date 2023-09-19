package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Action;
import com.model.Order;
import com.model.TraceOrderAction;
import com.repository.TraceOrderActionRepository;
import com.service.TraceOrderActionService;


@Service
public class TraceOrderActionServImpl implements TraceOrderActionService {

	@Autowired
	TraceOrderActionRepository traceOrderAction;
	
	
	public void logAction(Order order , Action action) {
		TraceOrderAction toa = new TraceOrderAction();
		toa.setId(null);
		toa.setOrder(order);
		toa.setAction(action);
		
		
		traceOrderAction.save(toa);
	}
	
	public void save(TraceOrderAction toa) {
		
	traceOrderAction.save(toa);
	}

	public List<TraceOrderAction> findAll(){
		return traceOrderAction.findAll();
	}
	
	public void addToDB(TraceOrderAction trace) {
		traceOrderAction.saveAndFlush(trace);
		
	}




}
