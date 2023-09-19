package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Table( name="t_actions", schema = "public")
public class Action {

	
	
	 Action(Long id) {
		this.id = id;
	}

	public Action(Long id, String code, String name, Double minuteRate) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.minuteRate = minuteRate;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_actions_id_seq")
    @SequenceGenerator(name = "t_actions_id_seq", sequenceName = "t_actions_id_seq",allocationSize = 1)
	private Long id;

	@Column(name = "code", nullable= false)
	public String code;
	
	@Column(name = "name", nullable= false)
	public String name;
	
	@Column(name = "minute_rate", nullable= false)
	public Double minuteRate;
	
	
	
	
	
	
}
