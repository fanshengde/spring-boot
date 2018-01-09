package com.fsd.security;

import org.springframework.security.core.GrantedAuthority;

public class UrlGrantedAuthority implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String permissionUrl;
	private String method;

	public UrlGrantedAuthority(String permissionUrl, String method) {
		this.permissionUrl = permissionUrl;
		this.method = method;
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

	@Override
	public String getAuthority() {
		return this.permissionUrl + ";" + this.method;
	}

}
