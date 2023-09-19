package com.help;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public final class Helper {
	

	private Helper() {}
	
	public static Date getDateFrSql() {
		 LocalDate currentDate = LocalDate.now();
		 return   Date.valueOf(currentDate);
	}

	
	
	public static Timestamp getTimeStamp()  {
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		 Timestamp timestamp = Timestamp.valueOf(currentDateTime);
		 
	        return timestamp;
	}
	
	
	public static  String getDateInString() {
		
		LocalDate currentDate = LocalDate.now();
        String pattern = "yyyyMMdd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String dateString = currentDate.format(formatter);
        
        return dateString;
	}
	
	
	
	
	
	
	
	
	 
}
