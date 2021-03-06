package com.fsd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsd.bean.UserInfo;
import com.fsd.handler.Msg;
import com.fsd.service.UserService;

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
	
    @RequestMapping("/")
    public String index(Model model){
        Msg msg =  new Msg("测试标题","测试内容","欢迎来到HOME页面,您拥有 ROLE_HOME 权限");
        model.addAttribute("msg", msg);
        return "home";
    }
    
    @RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }
}