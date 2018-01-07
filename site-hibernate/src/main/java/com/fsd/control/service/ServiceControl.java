package com.fsd.control.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.domain.UserInfo;
import com.fsd.repository.UserInfoRepository;

@RestController
public class ServiceControl {
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@RequestMapping(value="/ctrRegister", method=RequestMethod.POST)
	public UserInfo addUser(@ModelAttribute UserInfo userInfo, Model model) {
		userInfoRepository.save(userInfo);
		System.out.println("fanshengde  -  success");
		return userInfo;
	}
}
