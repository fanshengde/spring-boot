package com.fsd.service;

import org.springframework.security.core.GrantedAuthority;

public class MyGrantedAuthority implements GrantedAuthority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String url;
	private String method;
	
	public MyGrantedAuthority(String url, String method) {
		this.url = url;
		this.method = method;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermissionUrl() {
		return url;
	}
	
	public void setPermissionUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	@Override
	public String getAuthority() {
		return this.url + ":" + this.method;
	}

}
