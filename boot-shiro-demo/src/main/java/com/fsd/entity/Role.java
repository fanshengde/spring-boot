package com.fsd.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fsd.entity.base.AbstractEntity;

@Entity
@Table(name = "sys_roles")
public class Role extends AbstractEntity {
	private String role;
	private String description;
	private Boolean available = Boolean.FALSE;

	public Role() {

	}

	public Role(String role, String description, Boolean available) {
		this.role = role;
		this.description = description;
		this.available = available;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
}
