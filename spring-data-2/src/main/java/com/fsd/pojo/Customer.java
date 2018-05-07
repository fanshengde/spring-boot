package com.fsd.pojo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.util.Assert;

@Entity
public class Customer extends AbstractEntity {
	private String firstname;
	private String lastname;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "customer_id")
	private Set<Address> address = new HashSet<Address>();

	@Column(unique = true)
	private EmailAddress emailAddress;

	protected Customer() {

	}

	public Customer(String firstname, String lastname) {
		Assert.hasText(firstname, "this firstname must not null");
		Assert.hasText(lastname, "this lastname must not null");
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public void add(Address address) {
		Assert.notNull(address, "address is must not be nulll or empty!");
		this.address.add(address);
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Set<Address> getAddress() {
		return Collections.unmodifiableSet(address);
	}

	public void setAddress(Set<Address> address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return firstname + " " + lastname + "-" + this.getSid() + " : " + emailAddress.toString();
	}
}
