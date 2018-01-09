package com.fsd.dao;

import java.util.List;
import java.util.Map;

import com.fsd.bean.PermissionInfo;
import com.fsd.bean.UserInfo;

public interface PermissionInfoDao {
	List<UserInfo> getByMap(Map<String, Object> map);

	PermissionInfo getBySid(Integer sid);

	Integer create(PermissionInfo permissionInfo);

	int update(PermissionInfo permission);

	List<PermissionInfo> getByUserSid(Integer userSid);
}