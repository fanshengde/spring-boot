package com.fsd.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fsd.entity.base.AbstractEntity;

@Entity
@Table(name = "sys_users")
public class User extends AbstractEntity {
	private String username;
	private String password;
	private String salt;
	private Boolean locked = Boolean.FALSE;

	public User() {

	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String salt) {
		this.username = username;
		this.password = password;
		this.salt = salt;
	}

	public String getCredentialsSalt() {
		return username + salt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
}
