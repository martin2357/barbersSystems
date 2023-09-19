package com.dto;

import java.util.List;

import com.model.enums.Status;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Value
@Jacksonized
public class OrderDTO {

	private Long id;
	
	private double totalPrice;
	
	private String number;
	
	private int employeeID;
	
	private String employeeName;
	
	private String employeeCode;
	
	private int customerID;
	
	private	String customerName;
	
	private	String customerCode;
	
	private List<ActionDTO>actions;
	
	private Status status;
	
	
	
	
}
