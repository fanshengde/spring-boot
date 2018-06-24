package com.fsd.service;

import com.fsd.entity.Role;

public interface RoleService {
	public Role createRole(Role role);

	public void deleteRole(Role role);
	
	
	public void correlationPermissions(Long roleId, Long... permissionIds);
	
	public void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
