package com.fsd.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
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
		Collection<ConfigAttribute> co = new ArrayList<>();
		co.add(new SecurityConfig("null"));
		return co;
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
