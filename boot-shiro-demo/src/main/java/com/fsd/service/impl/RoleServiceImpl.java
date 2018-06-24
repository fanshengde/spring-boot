package com.fsd.service.impl;

import com.fsd.dao.PermissionRepository;
import com.fsd.dao.RolePermissionRepository;
import com.fsd.dao.RoleRepository;
import com.fsd.entity.Permission;
import com.fsd.entity.Role;
import com.fsd.entity.RolePermission;
import com.fsd.service.RoleService;

public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	private PermissionRepository permissionRepository;

	private RolePermissionRepository rolePermissionRepository;

	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
		this.roleRepository = roleRepository;
		this.permissionRepository = permissionRepository;
	}

	public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository,
			RolePermissionRepository rolePermissionRepository) {
		this.roleRepository = roleRepository;
		this.permissionRepository = permissionRepository;
		this.rolePermissionRepository = rolePermissionRepository;
	}

	@Override
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void deleteRole(Role role) {
		roleRepository.delete(role);
	}

	/**
	 * 添加角色-权限之间关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		Role role = roleRepository.findById(roleId).get();
		RolePermission rolePermission;

		for (Long permissionId : permissionIds) {
			Permission permission = permissionRepository.findById(permissionId).get();
			rolePermission = new RolePermission(role, permission);
			rolePermissionRepository.save(rolePermission);
		}
	}

	/**
	 * 移除角色-权限之间关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		Role role = roleRepository.findById(roleId).get();
		RolePermission rolePermission;
		Permission permission;
		for (Long permissionId : permissionIds) {
			permission = permissionRepository.findById(permissionId).get();
			rolePermission = rolePermissionRepository.findByRoleAndPermission(role, permission);
			rolePermissionRepository.deleteById(rolePermission.getSid());
		}
	}

}
