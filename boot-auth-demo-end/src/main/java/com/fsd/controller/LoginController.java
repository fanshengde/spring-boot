package com.fsd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsd.bean.UserInfo;
import com.fsd.service.UserInfoService;

@Controller
public class LoginController {
	@Autowired
	UserInfoService userInfoService;

	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(@AuthenticationPrincipal UserInfo loginedUser,
			@RequestParam(name = "logout", required = false) String logout) {
		if (logout != null) {
			return null;
		}
		if (loginedUser != null) {
			return userInfoService.getBySid(loginedUser.getSid());
		}
		return null;
	}
}
