package com.fsd.bean;

import javax.persistence.Entity;

import com.fsd.bean.base.AbstractEntity;

@Entity
public class PermissionInfo extends AbstractEntity {
	private String cnname;
	private String permissionUrl;
	private String method;
	private String description;
	
	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
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
		return "Permission{" + "sid=" + getSid() + ", name=" + cnname + ", permissionUrl=" + permissionUrl + ", method="
				+ method + ", description=" + description + '}';
	}
}
