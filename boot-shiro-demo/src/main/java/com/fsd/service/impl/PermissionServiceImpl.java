package com.fsd.service.impl;

import com.fsd.dao.PermissionRepository;
import com.fsd.entity.Permission;
import com.fsd.service.PermissionService;

public class PermissionServiceImpl implements PermissionService {

	PermissionRepository permissionRepository;

	public PermissionServiceImpl(PermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}

	@Override
	public void createPermission(Permission permission) {
		permissionRepository.save(permission);
	}

	@Override
	public void deletePermission(Permission permission) {
		permissionRepository.delete(permission);
	}

	@Override
	public void deletePermissionById(Long sid) {
		permissionRepository.deleteById(sid);
	}

}
