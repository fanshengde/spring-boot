package com.fsd.bean;

import javax.persistence.Entity;

import com.fsd.bean.base.AbstractEntity;

@Entity
public class RoleInfo extends AbstractEntity implements Comparable<RoleInfo> {
	private String cnname;
	private Integer roleLevel;
	private String description;
	private String menuItems;

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public Integer getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(String menuItems) {
		this.menuItems = menuItems;
	}

	@Override
	public int compareTo(RoleInfo o) {
		if (getSid() == o.getSid()) {
			return 0;
		} else if (getSid() > o.getSid()) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof RoleInfo) {
			if (getSid() == ((RoleInfo) obj).getSid()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Role{" + "sid=" + getSid() + ", sname=" + cnname + ", roleLevel=" + roleLevel + ", description="
				+ description + '}';
	}
}
