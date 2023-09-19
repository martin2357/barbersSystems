package com.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class CustomerViewDTO {

	Long id;
	String name;
	String code;
	String email;
}
