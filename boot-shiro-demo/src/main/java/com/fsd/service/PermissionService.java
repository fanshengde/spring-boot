package com.fsd.service;

import com.fsd.entity.Permission;

public interface PermissionService {
	public void createPermission(Permission permission);

	public void deletePermission(Permission permission);
	
	public void deletePermissionById(Long sid);
}
