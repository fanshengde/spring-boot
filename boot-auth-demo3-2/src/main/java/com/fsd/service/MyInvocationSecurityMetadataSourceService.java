package com.fsd.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.fsd.dao.PermissionDao;
import com.fsd.domain.Permission;
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource{
	
	@Autowired
	private PermissionDao permissionDao;
	
	private HashMap<String, Collection<ConfigAttribute>> map = null;

    /**
     * 加载权限表中所有权限
     */
	public void loadResouurceDefine() {
		map = new HashMap<>();
		Collection<ConfigAttribute> array;
		
		ConfigAttribute cfg;
		
		List<Permission> permissions= permissionDao.findAll();
		
		for(Permission permission : permissions) {
			array = new ArrayList<>();
			cfg = new SecurityConfig(permission.getName());
			
			array.add(cfg);
			
			map.put(permission.getUrl(),array);
		}
	}
	
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if(map == null)
			loadResouurceDefine();
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest(); 
		
		AntPathRequestMatcher matcher;
		
		String resUrl;
		
		for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
			resUrl = iter.next();
			matcher = new AntPathRequestMatcher(resUrl);
			if(matcher.matches(request)) {
				return map.get(resUrl);
			}
		}
		
		
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
