package com.fsd.repository;

import org.springframework.data.repository.CrudRepository;

import com.fsd.domain.SysUser;

public interface SysUserRepository extends CrudRepository<SysUser, Long>{

}
