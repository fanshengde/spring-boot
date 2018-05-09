package com.fsd.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fsd.bean.base.AbstractEntity;

@Entity
public class RolePermission extends AbstractEntity {
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private RoleInfo roleInfo = new RoleInfo();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "permission_id")
	private PermissionInfo permissionInfo = new PermissionInfo();

	public RoleInfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	public PermissionInfo getPermissionInfo() {
		return permissionInfo;
	}

	public void setPermissionInfo(PermissionInfo permissionInfo) {
		this.permissionInfo = permissionInfo;
	}

	// @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	// @JoinColumn(name = "permission_id")
	// private Set<PermissionInfo> userPermission = new HashSet<PermissionInfo>();

}
