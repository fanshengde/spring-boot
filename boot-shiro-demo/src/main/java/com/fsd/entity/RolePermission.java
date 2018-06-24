package com.fsd.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fsd.entity.base.AbstractEntity;

@Entity
@Table(name = "sys_roles_permissions")
public class RolePermission extends AbstractEntity {
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private Role role;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "permission_id")
	private Permission permission;

	public RolePermission() {

	}

	public RolePermission(Role role, Permission permission) {
		this.role = role;
		this.permission = permission;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}
