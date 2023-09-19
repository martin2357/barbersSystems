package com.model;

import java.util.ArrayList;
import java.util.List;

import com.model.abstracts.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table( name="t_customers", schema = "public")
public class Customer extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_customers_id_seq")
    @SequenceGenerator(name = "t_customers_id_seq", sequenceName = "t_customers_id_seq",allocationSize = 1)
	private Long id;

	@OneToMany
	@JoinColumn(name = "customer_id")
	private List<Order>orders = new ArrayList<>();
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public Customer(String code, String name, String lastName, String email, String password) {
		super();
		this.code = code;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}


	public Customer() {
		super();
	}
	
	
	
	
}
