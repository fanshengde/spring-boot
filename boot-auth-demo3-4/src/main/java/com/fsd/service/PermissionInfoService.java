package com.fsd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.bean.PermissionInfo;
import com.fsd.dao.PermissionInfoDao;

@Service
public class PermissionInfoService {

	@Autowired
	private PermissionInfoDao permissionDao;

	public List<PermissionInfo> getByMap(Map<String, Object> map) {
		return permissionDao.getByMap(map);
	}

	public PermissionInfo getById(Integer id) {
		return permissionDao.getById(id);
	}

	public PermissionInfo create(PermissionInfo permissionInfo) {
		permissionDao.create(permissionInfo);
		return permissionInfo;
	}

	public PermissionInfo update(PermissionInfo permissionInfo) {
		permissionDao.update(permissionInfo);
		return permissionInfo;
	}

	public int delete(Integer id) {
		return permissionDao.delete(id);
	}
}
