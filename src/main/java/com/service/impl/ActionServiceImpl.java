package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repository.ActionRepository;
import com.service.ActionService;
import com.model.Action;


@Service
public class ActionServiceImpl implements ActionService {

	@Autowired
	ActionRepository actionRepository;
	
	public List<Action>findAll(){
		return actionRepository.findAll();
	}
	
	public void addToDB(Action action) {
		actionRepository.saveAndFlush(action);
	}
	
	public void delete(Long id) {
		actionRepository.deleteById(id);
	}
	
}
