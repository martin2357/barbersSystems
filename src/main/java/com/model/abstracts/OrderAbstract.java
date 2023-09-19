package com.model.abstracts;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.sql.Timestamp;

@MappedSuperclass
public abstract class OrderAbstract {

	@Column(name = "number", nullable= false)
	private String number;
	
	@Column(name = "ordering_time", nullable= false)
	private Timestamp orderingTime;

	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Timestamp getOrderingTime() {
		return orderingTime;
	}

	public void setOrderingTime(Timestamp orderingTime) {
		this.orderingTime = orderingTime;
	}
	
	
	
	
}
