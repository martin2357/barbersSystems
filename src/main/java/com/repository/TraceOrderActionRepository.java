package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.TraceOrderAction;

@Repository
public interface TraceOrderActionRepository extends JpaRepository<TraceOrderAction, Long> {


}
