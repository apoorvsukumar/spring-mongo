package com.apoorv.api.model;

public class User {
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User(String status) {
		super();
		this.status = status;
	}

}
