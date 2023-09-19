package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Employee;
import com.model.Order;
import com.model.enums.Status;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	@Query("SELECT o FROM Order o WHERE o.status = :status AND o.employeeID = :employeeID")
	List<Order>findOrderswithStatus(@Param("employeeID") Long employeeID, @Param("status") Status status);
	
	Employee findByEmail(String email);
	
	@Query("SELECT e FROM Employee e WHERE e.code = :code AND e.password = :password")
	Employee findByCodeAndPassword(@Param("code") String code, @Param("password") String password);

	
	@Query("SELECT e.code FROM Employee e WHERE e.id = (SELECT MAX(id) FROM Employee)")
	String getLastCode();
}
