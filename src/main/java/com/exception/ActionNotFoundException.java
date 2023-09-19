package com.exception;


public class ActionNotFoundException extends RuntimeException{

	public ActionNotFoundException(Long actionId) {
		super("action with id " + actionId+ " was not found");
	}
}

