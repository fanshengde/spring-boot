package com.fsd.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fsd.bean.base.AbstractEntity;

@Entity
public class UserRole extends AbstractEntity {
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private RoleInfo roleInfo = new RoleInfo();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private UserInfo userInfo = new UserInfo();

	public RoleInfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
