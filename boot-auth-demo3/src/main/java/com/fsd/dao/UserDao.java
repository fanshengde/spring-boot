package com.fsd.dao;

import com.fsd.domain.SysUser;
public interface UserDao {
	public SysUser findByUserName(String username);
}
