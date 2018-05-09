package com.fsd.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.Assert;

@Entity
@Table(name="customer")
public class Customer extends AbstractEntity{
	private String firstname;
	private String lastname;

	@Column(unique = true)
	private EmailAddress emailAddress;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Address> addresses = new HashSet<Address>();

	protected Customer() {

	}

	public Customer(String firestname, String lastname) {
		Assert.hasText(firestname, "'firestname' 必须是字符");
		Assert.hasText(lastname, "'lastname' 必须是字符");
		this.firstname = firestname;
		this.lastname = lastname;
	}

	public void add(Address address) {
		Assert.notNull(address, "'address' 不能为空");
		this.addresses.add(address);
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

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}
