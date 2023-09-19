package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Customer;
import com.model.Order;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	
		@Query("SELECT o FROM Order o WHERE o.status = 'V'")
		 List<Order>findOrderswithV();
		 
		Customer findByEmail(String email);
		
		@Query("SELECT e FROM Customer e WHERE e.code = :code AND e.password = :password")
		Customer findByCodeAndPassword(@Param("code") String code, @Param("password") String password);
		
		@Query("SELECT e.code FROM Customer e WHERE e.id = (SELECT MAX(id) FROM Customer)")
		String getLastCode();
}
