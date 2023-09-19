package com.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class ActionViewDTO {
	
	private String code;
	private String name;

}
