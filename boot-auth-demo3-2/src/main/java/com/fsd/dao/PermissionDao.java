package com.fsd.dao;

import java.util.List;

import com.fsd.domain.Permission;

public interface PermissionDao {
	public List<Permission> findAll();
	public List<Permission> findByAdminUserId(int userId);
}
