package com.fsd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.entity.user.UserInfo;
import com.fsd.repository.user.UserInfoRepository;


@Service
public class UserInfoService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	public List<UserInfo> findAll(){
		return (List<UserInfo>) userInfoRepository.findAll();
	}
	
	public UserInfo create(UserInfo user) {
		return userInfoRepository.save(user);
	}
	
	public UserInfo findUserById(long id) {
		return userInfoRepository.findOne(id);
	}
	
	public UserInfo login(String email, String password) {
		return userInfoRepository.findByEmailAndPassword(email, password);
	}
	
	public UserInfo update(UserInfo user) {
		return userInfoRepository.save(user);
	}
	
	public void deleteUser(long id) {
		userInfoRepository.delete(id);
	}
	
	public UserInfo findUserByEmail(String email) {
		return userInfoRepository.findUserByEmail(email);
	}
	
}
