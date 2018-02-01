package com.fsd.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;

public class UrlConfigAttribute implements ConfigAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private final HttpServletRequest httpServletRequest;

	public UrlConfigAttribute(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	@Override
	public String getAttribute() {
		return null;
	}

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

}
