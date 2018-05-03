package com.fsd.pojo;

import javax.persistence.Entity;

import org.springframework.util.Assert;

@Entity
public class Address extends AbstractEntity {
	private String street;
	private String city;
	private String country;

	protected Address() {

	}

	public Address(String street, String city, String country) {
		Assert.hasText(street, "Street must not be null or empty!");
		Assert.hasText(city, "city must not be null or empty!");
		Assert.hasText(country, "country not be null or empty!");
		this.street = street;
		this.city = city;
		this.country = country;
	}
	
	public Address getCopy() {
		return new Address(this.street, this.city, this.country);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
