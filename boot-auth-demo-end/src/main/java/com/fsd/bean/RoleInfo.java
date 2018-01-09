package com.fsd.bean;

public class RoleInfo implements Comparable<RoleInfo> {
	private Integer sid;
	private String sname;
	private Integer roleLevel;
	private String description;
	private String menuItems;

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
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
		if (sid == o.getSid()) {
			return 0;
		} else if (sid > o.getSid()) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RoleInfo) {
			if (this.getSid() == ((RoleInfo) obj).getSid()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Role{" + "sid=" + sid + ", sname=" + sname + ", roleLevel=" + roleLevel + ", description=" + description
				+ '}';
	}
}
