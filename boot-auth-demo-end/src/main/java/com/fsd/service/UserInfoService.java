package com.fsd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import com.fsd.bean.UserInfo;
import com.fsd.dao.UserInfoDao;

@Service
public class UserInfoService {
	@Autowired
	private SessionRegistry sessionRegistry;

	@Autowired
	private UserInfoDao userInfoDao;

	public UserInfo getBySid(Integer sid) {
		UserInfo userInfo = userInfoDao.getById(sid);
		return userInfo;
	}
}
