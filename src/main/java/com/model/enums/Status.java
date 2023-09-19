package com.model.enums;


public enum Status {
	
	EXPANDED('E'),
	VERIFIED('V') ,
	CANCELLATION('C');

		
	private final char databaseValue; 
	
	
	Status(char databaseValue) {
        this.databaseValue = databaseValue;
    }

	
	public char getDatabaseValue() {
		return databaseValue;
	}
	
	
	//získavanie dát z DB - char to enum
	public static Status getOrderStatusFromDatabaseValue(char databaseValue) {
		for (Status status : Status.values()) {
            if (status.getDatabaseValue() == databaseValue) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid database value for Status: " + databaseValue);
    }
	
	
	//String to enum
	public static Status fromString(String statusString) {
        for (Status status : Status.values()) {
            if (status.name().equalsIgnoreCase(statusString)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status string: " + statusString);
    }

}
