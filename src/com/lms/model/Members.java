package com.lms.model;

public class Members {
	private int id;
	private String name;
	private String email;
	private String role;
	
	Members(){}
	
	public Members(String name, String email, String role){
		this.name = name;
		this.email = email;
		this.role = role;
	}
	
	// getters & setters
	
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		
	}
	public String getName() {
		return name;
	}
	
	public void setEmail(String email) {
		this.email =email;
	}
	public String getEmail() {
		return email;
	}
	
	public void setRole(String role) {
		this.role =role;
	}
	public String getRole() {
		return role;
	}

}
