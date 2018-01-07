package com.fsd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.bean.UserInfo;
import com.fsd.dao.UserInfoDao;

@Service
public class UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;
	
	public List<UserInfo> getByMap(Map<String, Object> map){
		return userInfoDao.getByMap(map);
	}
	
	public UserInfo getById(Integer id) {
		return userInfoDao.getById(id);
	}
	
	public UserInfo create(UserInfo userInfo	) {
		userInfoDao.create(userInfo);
		return userInfo;
	}
	
	public UserInfo update(UserInfo userInfo) {
		userInfoDao.update(userInfo);
		return userInfo;
	}
	
	public int delete(Integer id) {
		return userInfoDao.delete(id);
	}
}
