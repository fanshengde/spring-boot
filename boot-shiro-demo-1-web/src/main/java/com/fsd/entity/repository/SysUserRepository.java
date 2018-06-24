package com.fsd.entity.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fsd.entity.SysUser;

public interface SysUserRepository extends PagingAndSortingRepository<SysUser, Long> {
	public SysUser findByUsername(String username);
}
