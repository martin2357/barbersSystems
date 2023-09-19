package com.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table( name="t_orders_actions", schema = "public")
public class TraceOrderAction {

	
	public TraceOrderAction(Order order, Action action) {
		super();
		this.order = order;
		this.action = action;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_orders_actions_action_id_seq")
	@SequenceGenerator(name = "t_orders_actions_action_id_seq", sequenceName = "t_orders_actions_action_id_seq",allocationSize = 1)
	private Long id;
	 
	@ManyToOne
	@JoinColumn(name = "order_id", nullable= false)
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "action_id", nullable= false)
	private Action action;

	
	public String getActionName() {
	    return action.getName();
	}

	public Double getActionMinuteRate() {
	    return action.getMinuteRate();
	}
	
	
}
