package com.fsd.pojo;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.springframework.util.Assert;

@Entity
public class Product extends AbstractEntity {
	@Column(nullable = false)
	private String name;

	private String description;

	private BigDecimal price;

	@ElementCollection
	private Map<String, String> attributes = new HashMap<String, String>();

	protected Product() {

	}

	public Product(String name, BigDecimal price) {
		this(name, price, null);
	}

	public Product(String name, BigDecimal price, String description) {
		Assert.hasText(name, "Name must not be null or empty!");
		Assert.isTrue(BigDecimal.ZERO.compareTo(price) < 0, "Price must be grater than zero!");
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Map<String, String> getAttributes() {
		return Collections.unmodifiableMap(attributes);
	}

	public void setAttributes(String name, String value) {
		Assert.hasText(name, "name is not be null or empty!");
		if (value == null) {
			this.attributes.remove(value);
		} else {
			this.attributes.put(name, value);
		}
	}

	@Override
	public String toString() {
		return name + " : " + description + " : " + price + " : " + attributes.toString();
	}

}
