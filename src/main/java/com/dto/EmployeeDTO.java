package com.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class EmployeeDTO {

	@NotNull
	private String code;
	
	@NotNull
	private String name;
	
	@NotNull
	private String lastName;
	
	@NotNull	
	private String email;
	
	private List<OrderViewDTO> orders;
}
