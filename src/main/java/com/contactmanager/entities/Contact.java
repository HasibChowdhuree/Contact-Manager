package com.contactmanager.entities;

import javax.persistence.Embeddable;

@Embeddable
public class Contact {
	private String name, email, phone, city, imageurl, description;
	public Contact() {
		super();
	}
	public Contact(String name, String email, String phone, String city, String imageurl, String description) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.city = city;
		this.imageurl = imageurl;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
