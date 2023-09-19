package com.model;

import java.util.ArrayList;
import java.util.List;

import com.model.abstracts.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table( name="t_employees", schema = "public")
public class Employee extends User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_employees_id_seq")
    @SequenceGenerator(name = "t_employees_id_seq", sequenceName = "t_employees_id_seq",allocationSize = 1)
	private Long id;

	@OneToMany
	@JoinColumn(name= "employee_id")
	private List<Order>orders =  new ArrayList<Order>();
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	 @Column(name = "email", nullable = false)
	    protected String email;
	
	 public String getEmail() {
	        return email;
	    }
	
}
