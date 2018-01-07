package com.fsd.dao;

import java.util.List;
import java.util.Map;

import com.fsd.bean.RoleInfo;

public interface RoleInfoDao {
		public List<RoleInfo> getByMap(Map<String, Object> map);
		public RoleInfo getById(Integer id);
		public Integer create(RoleInfo roleInfo);
		public int update(RoleInfo roleInfo);
		public int delete (Integer id);
}
