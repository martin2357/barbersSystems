package com.model;

import lombok.Data;


@Data
public class OrderEmployeeInfo {

	private String employeeName;
    private String orderNumber;

    public OrderEmployeeInfo(String employeeName, String orderNumber) {
        this.employeeName = employeeName;
        this.orderNumber = orderNumber;
    }

}
