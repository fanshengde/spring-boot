package com.fsd.dao;

import java.util.List;
import java.util.Map;

import com.fsd.bean.PermissionInfo;

public interface PermissionInfoDao {
	public List<PermissionInfo> getByMap(Map<String, Object> map);
	public PermissionInfo getById(Integer id);
	public Integer create (PermissionInfo permissionInfo);
	public int update(PermissionInfo permission);
	public int delete(Integer id);
	public List<PermissionInfo> getList();
	public List<PermissionInfo> getByUserId(Integer userId);
}
