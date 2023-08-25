package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_employees_id_seq")
    @SequenceGenerator(name = "t_employees_id_seq", sequenceName = "t_employees_id_seq",allocationSize = 1)
	private Long id;

	@Column(name = "code", nullable= false)
	private String code;
	
	@Column(name = "name", nullable= false)
	private String name;
	
	@Column(name = "last_name", nullable= false)
	private String lastName;
	
	@Column(name = "email", nullable= false)
	private String email;

	@Column(name = "password", nullable= false)
	private String password;
}
