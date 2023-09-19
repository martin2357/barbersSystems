package com.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class CustomerDTO {

	
	private Long id;
	
	@NotNull
	@Size(min = 1, max = 30)
	private String name;
	
	@NotNull
	@Size(min = 1, max = 4)
	private String code;
	
	@NotNull
	private String email;
	
	
	private List<OrderViewDTO> orders;
}
