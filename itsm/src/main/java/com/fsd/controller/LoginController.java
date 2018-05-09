package com.fsd.controller;

import com.fsd.bean.UserInfo;
import com.fsd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login")
	public Object login(@AuthenticationPrincipal UserInfo loginedUser,
			@RequestParam(name = "logout", required = false) String logout) {
		if (logout != null) {
			return null;
		}
		if (loginedUser != null) {
			return userService.getById(loginedUser.getSid());
		}
		return "login";
	}
}