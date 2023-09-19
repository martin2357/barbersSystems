package com.model.abstracts;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class User {

	
	@Column(name = "code", nullable= false)
	protected String code;
	
	@Column(name = "name", nullable= false)
	protected String name;
	
	@Column(name = "last_name", nullable= false)
	protected String lastName;
	
	@Column(name = "email", nullable= false)
	protected String email;

	@Column(name = "password", nullable= false)
	protected String password;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	public void incrementCode(String code) throws Exception {
	    if (code != null) {
	        try {
	            int currentCode = Integer.parseInt(code);
	            if (currentCode < 999) {
	                currentCode++;
	            } else {
	                currentCode = 0;
	            }
	            code = String.format("%04d", currentCode);
	            setCode(code);
	        } catch (NumberFormatException e) {
	        	throw new Exception("Neplatný vstup: Očakávaný číselný formát.");
	        }
	        
	    }
	}
	
}
