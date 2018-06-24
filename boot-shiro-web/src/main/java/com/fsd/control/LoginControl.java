package com.fsd.control;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fsd.entity.Users;

@Controller
public class LoginControl {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public Object index2login(Model model) {
		model.addAttribute("users", new Users());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Object login(@ModelAttribute Users user, Model model) {
		String error = null;
		String username = user.getUsername();
		String password = user.getPassword();

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);

		try {
			subject.login(token);
		} catch (UnknownAccountException e) {
			error = "用户名/密码错误";
		} catch (IncorrectCredentialsException e) {
			error = "用户名/密码错误";
		} catch (AuthenticationException e) {
			error = "其他错误" + e.getMessage();
		}

		if (error != null) { // 登陆失败
			model.addAttribute("error", error);
			return "login";
		} else {// 登陆成功
			return "loginSuccess";
		}

	}
}
