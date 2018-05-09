package com.fsd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import com.fsd.bean.UserInfo;
import com.fsd.dao.UserInfoRepository;

@Service
public class UserService {
//	@SuppressWarnings("unused")
//	@Autowired
//	private SessionRegistry sessionRegistry;

	@Autowired
	private UserInfoRepository userInfoRepository;

	public UserInfo getById(Long id) {
		// UserInfo user = userInfoRepository.getById(id);
		UserInfo user = userInfoRepository.findById(id).get();
		return user;
	}

}