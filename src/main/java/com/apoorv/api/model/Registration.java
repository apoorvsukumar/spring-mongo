package com.apoorv.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="registration")
public class Registration {
	
	public Registration() {}

	public Registration(String id, String name, String address, String country, String state, String email,
			String password, String pan, String contactNo, String dob, String oldEmail) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.country = country;
		this.state = state;
		this.email = email;
		this.password = password;
		this.pan = pan;
		this.contactNo = contactNo;
		this.dob = dob;
		this.oldEmail = oldEmail;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", name=" + name + ", address=" + address + ", country=" + country
				+ ", state=" + state + ", email=" + email + ", password=" + password + ", pan=" + pan + ", contactNo="
				+ contactNo + ", dob=" + dob + "]";
	}

	@Id
	private String id;
	private String name;
	private String address;
	private String country;
	private String state;
	
	@Indexed(unique = true)
	private String email;
	
	private String password;
	private String pan;
	private String contactNo;
	private String dob;
	private String oldEmail;

	public String getOldEmail() {
		return oldEmail;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

}
