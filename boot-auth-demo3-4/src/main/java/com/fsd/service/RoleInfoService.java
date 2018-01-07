package com.fsd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.bean.RoleInfo;
import com.fsd.dao.RoleInfoDao;

@Service
public class RoleInfoService {
	
		@Autowired
		private RoleInfoDao roleInfoDao;
		
		public List<RoleInfo> getByMap(Map<String, Object> map){
			return roleInfoDao.getByMap(map);
		}
		
		public RoleInfo getById(Integer id) {
			return roleInfoDao.getById(id);
		}
		
		public RoleInfo create(RoleInfo roleInfo) {
			roleInfoDao.create(roleInfo);
			return roleInfo;
		}
		
		public RoleInfo update(RoleInfo roleInfo) {
			roleInfoDao.update(roleInfo);
			return roleInfo;
		}
		
		public int delete(Integer id) {
			return roleInfoDao.delete(id);
		}
}
