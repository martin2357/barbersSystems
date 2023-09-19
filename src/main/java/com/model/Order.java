package com.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.help.StatusConverter;
import com.model.abstracts.OrderAbstract;
import com.model.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Entity
@Table( name="t_orders", schema = "public")
public class Order extends OrderAbstract {
	
	
	// Toto sluzi na testovanie pre vstupe dat z DataInit.class
	/*
	public Order(Long id, Date dateExpousure, int employeeID, int customerID, Status status,
			List<TraceOrderAction> actions) {
		super();
		this.id = id;
		this.dateExpousure = dateExpousure;
		this.employeeID = employeeID;
		this.customerID = customerID;
		this.status = status;
		this.actions = actions;
	}
	*/

	
	public Order(Long id, Date dateExpousure, int employeeID, int customerID, Status status, List<Long> actionIDs) {
	    this.id = id;
	    this.dateExpousure = dateExpousure;
	    this.employeeID = employeeID;
	    this.customerID = customerID;
	    this.status = status;

	    this.actions = new ArrayList<>();
	    for (Long actionID : actionIDs) {
	        TraceOrderAction traceOrderAction = new TraceOrderAction();
	        traceOrderAction.setOrder(this);
	        traceOrderAction.setAction(new Action(actionID)); // Predpokladáme, že máte konštruktor Action s parametrom ID
	        this.actions.add(traceOrderAction);
	    }
	}
	
	
	
	
	public Order() {}
	

	@Transient // Táto anotácia zabezpečí, že sa táto premenná neuloží do databázy
    private List<Long> actionIDs;
	
		public void setActionIDs(List<Long> actionIDs) {
	        this.actionIDs = actionIDs;
	        this.actions = new ArrayList<>();
	        for (Long actionID : actionIDs) {
	            TraceOrderAction traceOrderAction = new TraceOrderAction();
	            traceOrderAction.setOrder(this);
	            traceOrderAction.setAction(new Action(actionID)); 
	            this.actions.add(traceOrderAction);
	        }
	    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_orders_id_seq")
    @SequenceGenerator(name = "t_orders_id_seq", sequenceName = "t_orders_id_seq",allocationSize = 1)
	private Long id;
	
	// dátum vystavenia
	@Column(name = "date_expousure", nullable= false)
	private Date dateExpousure;
	
	@Column(name = "employee_id", nullable= false)
	private int employeeID;
	
	@Column(name = "customer_id", nullable= false)
	private int customerID;
	
    @Convert(converter = StatusConverter.class)
    @Column(name = "status") 
    private Status status;
	
	
    @JsonIgnore
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<TraceOrderAction>actions = new ArrayList<>();
        
    public List<TraceOrderAction> getActions() {
        return actions;
    }
    
    
  
    
    public List<Action> getActList() {
        List<Action> actions = new ArrayList<>();
        for (TraceOrderAction traceOrderAction : this.actions) {
            actions.add(traceOrderAction.getAction());
        }
        return actions;
    }

    
    public void addAction(Action action) {
        TraceOrderAction toa = new TraceOrderAction(this, action);
        actions.add(toa);
       
    }
    
    
    public void addAllAction(List<Action> xaction) {
    	for (Iterator iterator = xaction.iterator(); iterator.hasNext();) {
			Action action = (Action) iterator.next();
			TraceOrderAction toa = new TraceOrderAction(this, action);
			actions.add(toa);
		}
    	
    }

    public void removeAction(Action action) {
        TraceOrderAction toRemove = null;
        for (TraceOrderAction toa : actions) {
            if (toa.getAction().equals(action)) {
                toRemove = toa;
                break;
            }
        }
        if (toRemove != null) {
            actions.remove(toRemove);
        }
    }

    
    public double  getTotalPrice() {
    	Double totalPrice=0.0;
    	List<TraceOrderAction> toa = getActions();
    	for (Iterator iterator = toa.iterator(); iterator.hasNext();) {
			TraceOrderAction traceOrderAction = (TraceOrderAction) iterator.next();
			totalPrice += traceOrderAction.getActionMinuteRate();
		}
    	
    	return totalPrice;
    	
    }


    
	
}
