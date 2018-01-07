package com.fsd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.pojo.UserInfo;
import com.fsd.repository.UserRepository;

//@Controller
@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping(value="/user/getuserById/{sid}", method = RequestMethod.GET)
	@ResponseBody
	public String getUserByid(@PathVariable Long sid) {
		System.out.println("hello user 111");
		System.out.println(sid);
//		sid = Long.valueOf(1);
		UserInfo u = userRepository.findOne(sid);
		System.out.println(userRepository);
		System.out.println(u);
		if(u == null) {
			return "没有任何数据";
		}
		return u.toString();
	}
	
	@RequestMapping(value="/user/findUserByName/{username}")  
	@ResponseBody
	public UserInfo findUserByName(@PathVariable String username) {
		System.out.println("hello user22");
		
		UserInfo u = userRepository.findUserByName(username);
		
		System.out.println(u);
		
		return u;
	}
}
