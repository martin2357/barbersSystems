package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Action;

public interface ActionRepository extends JpaRepository<Action, Long>{

}
