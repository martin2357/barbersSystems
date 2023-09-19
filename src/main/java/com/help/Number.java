package com.help;

public class Number {
	
	private String number;
	
	private static Number instance;

	private Number() {
		number= ""; 
	}
	
	public static Number getInstance() {
		if(instance == null) {
			synchronized (Number.class) {
				if(instance == null) {
					instance = new Number();
				}
				
			}
		}
		return instance;
	}
	
	  public synchronized String getNewOrderNumber(String lastOrderNumber) {
		  number = lastOrderNumber;
		  cutlast3Chars();
		  incrementOrderNumb();
		  
	      return Helper.getDateInString() + number;
	    }
	
	
	
	private synchronized void incrementOrderNumb() {
		int intValue = Integer.valueOf(number);
		
		if (intValue >= 99999) {
	    	intValue = 0;
	    }	else { intValue++;}
		
		number = String.format("%03d", intValue);
	}
	
	private synchronized void cutlast3Chars() {
		number = number.substring(number.length() - 3);
	}
	
	
	
	
	
	
}
