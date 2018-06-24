package com.fsd.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fsd.entity.base.AbstractEntity;

@Entity
@Table(name = "sys_permissions")
public class Permission extends AbstractEntity {
	private String permission;
	private String description;
	private Boolean available = Boolean.FALSE;

	public Permission() {

	}

	public Permission(String permission, String description, Boolean available) {
		this.permission = permission;
		this.description = description;
		this.available = available;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
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
