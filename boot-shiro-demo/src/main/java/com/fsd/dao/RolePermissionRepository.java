package com.fsd.dao;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fsd.entity.Permission;
import com.fsd.entity.Role;
import com.fsd.entity.RolePermission;


public interface RolePermissionRepository extends PagingAndSortingRepository<RolePermission, Long>, QuerydslPredicateExecutor<RolePermission>{

	RolePermission findByRoleAndPermission(Role role, Permission permission);

}
