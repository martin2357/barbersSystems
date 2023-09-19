package com.dto;



public class ActionDTO {
    private String name;
    private Double minuteRate;

    public ActionDTO(String name, Double minuteRate) {
        this.name = name;
        this.minuteRate = minuteRate;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMinuteRate() {
		return minuteRate;
	}

	public void setMinuteRate(Double minuteRate) {
		this.minuteRate = minuteRate;
	}
    
    

   
}

