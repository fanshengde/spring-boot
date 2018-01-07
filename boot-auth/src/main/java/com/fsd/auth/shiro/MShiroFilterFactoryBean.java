package com.fsd.auth.shiro;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.BeanInitializationException;
import org.apache.shiro.mgt.SecurityManager;

public class MShiroFilterFactoryBean extends ShiroFilterFactoryBean {
	private Set<String> ignoreExt;
	
	public MShiroFilterFactoryBean() {
		super();
		
		ignoreExt = new HashSet<String>();
		ignoreExt.add(".jpg");
		ignoreExt.add(".png");
		ignoreExt.add(".gif");
		ignoreExt.add(".bmp");
		ignoreExt.add(".js");
		ignoreExt.add(".css");
	}
	
	@Override
	protected AbstractShiroFilter createInstance() throws Exception{
		SecurityManager securityManager = getSecurityManager();
		
		if(securityManager == null) {
			String msg = "SecurityManager property must be set";
			throw new BeanInitializationException(msg);
		}
		
		if(!(securityManager instanceof WebSecurityManager)) {
			String msg = "The security manager does not implement the WebSecurityManager interface.";
			throw new BeanInitializationException(msg);
		}
		
		FilterChainManager manager = createFilterChainManager();
		
		PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
		chainResolver.setFilterChainManager(manager);
		
		return new MSpringShiroFilter((WebSecurityManager) securityManager,	chainResolver);
	}
	
	private final class MSpringShiroFilter extends AbstractShiroFilter{
		protected MSpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {
			super();
			if(webSecurityManager == null) {
				throw new IllegalArgumentException("WEbSecurityManager property cannot be null.");
			}
			
			setSecurityManager(webSecurityManager);
			
			if(resolver != null) {
				setFilterChainResolver(resolver);
			}
		}
		
		@Override
		protected void doFilterInternal(ServletRequest ServletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
			HttpServletRequest request = (HttpServletRequest) ServletRequest;
			String str = request.getRequestURI().toLowerCase();
			
			boolean flag = true;
			int idx = 0;
			if((idx = str.indexOf(".")) > 0) {
				str = str.substring(idx);
				if(ignoreExt.contains(str.toLowerCase()))
					flag = false;
			}
			if(flag) {
				super.doFilterInternal(ServletRequest, servletResponse, chain);
			}else {
				chain.doFilter(ServletRequest, servletResponse);
			}
		}
	}
}
