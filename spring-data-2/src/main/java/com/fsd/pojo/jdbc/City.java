package com.fsd.pojo.jdbc;

import javax.persistence.Entity;

import com.fsd.pojo.AbstractEntity;

@Entity
public class City extends AbstractEntity {
	private String name;
	private String state;
	private String country;
	private String map;

	protected City() {

	}

	public City(String name, String state, String country, String map) {
		this.name = name;
		this.state = state;
		this.country = country;
		this.map = map;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return name + " : " + state + " : " + country + " : " + map + " <end>";
	}

}
