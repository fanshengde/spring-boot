package com.fsd.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.bean.UserInfo;
import com.fsd.service.UserInfoService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private UserInfoService userInfoService;

	@RequestMapping(method = RequestMethod.GET)
	public List<UserInfo> list(HttpServletRequest request) {
		return userInfoService.getByMap(null);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserInfo detail(@PathVariable Integer id) {
		return userInfoService.getById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public UserInfo create(@RequestBody UserInfo userInfo) {
		return userInfoService.create(userInfo);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public UserInfo update(@RequestBody UserInfo userInfo) {
		return userInfoService.update(userInfo);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public int delete(@PathVariable Integer id) {
		return userInfoService.delete(id);
	}
}
