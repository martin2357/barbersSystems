package com.help;

import com.model.enums.Status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

	@Override
	public String convertToDatabaseColumn(Status status) {
		 if (status == null) {
	            return null;
	        }
	        return String.valueOf(status.getDatabaseValue());
	}

	@Override
	public Status convertToEntityAttribute(String dbValue) {
		 if (dbValue == null) {
	            return null;
	        }
	        return Status.getOrderStatusFromDatabaseValue(dbValue.charAt(0));
	    }
	
	
	
	}

	

