package com.dto;

import java.sql.Date;
import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class OrderViewDTO {

	private String number;
	
	private Date dateExpousure;
	
	private Double price;
	
	private Enum status;
}
