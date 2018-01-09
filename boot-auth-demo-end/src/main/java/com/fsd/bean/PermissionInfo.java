package com.fsd.bean;

public class PermissionInfo {
	private Integer sid;
	private String sname;
	private String permissionUrl;
	private String method;
	private String description;

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

	public String getPermissionUrl() {
		return permissionUrl;
	}

	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Permission{" + "sid=" +sid + ", name=" + sname + ", permissionUrl=" + permissionUrl + ", method=" + method
				+ ", description=" + description + '}';
	}
}
