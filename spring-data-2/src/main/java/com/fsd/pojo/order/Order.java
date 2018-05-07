package com.fsd.pojo.order;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.Assert;

import com.fsd.pojo.AbstractEntity;
import com.fsd.pojo.Address;
import com.fsd.pojo.Customer;

@Entity
@Table(name = "Orders")
public class Order extends AbstractEntity {

	@ManyToOne(optional = false)
	private Customer customer;

	@ManyToOne
	private Address billingAddress;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private Address shippingAddress;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "order_id")
	private Set<LineItem> lineItems = new HashSet<LineItem>();

	protected Order() {

	}

	public Order(Customer customer, Address shippingAddress) {
		this(customer, shippingAddress, null);
	}

	public Order(Customer customer, Address shippingAddress, Address billingAddress) {
		Assert.notNull(customer, "customer is not must be null or empty!");
		Assert.notNull(shippingAddress, "shippingAddress is not must be null or empty!");

		this.customer = customer;
		this.shippingAddress = shippingAddress.getCopy();
		this.billingAddress = billingAddress == null ? null : billingAddress.getCopy();
	}

	public void add(LineItem lineItem) {
		this.lineItems.add(lineItem);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getBillingAddress() {
		return billingAddress != null ? billingAddress : shippingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Set<LineItem> getLineItems() {
		return Collections.unmodifiableSet(lineItems);
	}

	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;

		for (LineItem item : lineItems) {
			total = total.add(item.getTotal());
		}

		return total;
	}

}
