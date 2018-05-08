package com.fsd.pojo.jdbc;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fsd.pojo.AbstractEntity;

@Entity
public class Hotel extends AbstractEntity {
	private String name;
	private String address;
	
	@ManyToOne
	private City city;

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
