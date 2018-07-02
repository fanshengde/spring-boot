package com.fsd.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping({ "/", "/index" })
	public String index() {
		return "/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Map<String, Object> map) {
		LOG.info("HomeController.login()");

		String exception = (String) request.getAttribute("shiroLoginFailure");

		LOG.info("exception = " + exception);

		String msg = "";

		if (exception != null) {
			if (UnknownAccountException.class.getName().equals(exception)) {
				msg = "UnknownAccontException   账号不存在 : ";
				LOG.info(msg);
			} else if (IncorrectCredentialsException.class.getName().equals(exception)) {
				msg = "IncorrectCredentialsException -- > 密码不正确：\"";
				LOG.info(msg);
			} else if ("kaptchaValidateFailed".equals(exception)) {
				msg = "kaptchaValidateFailed -- > 验证码错误";
				LOG.info(msg);
			} else {
				msg = "else >> " + exception;
				LOG.info(msg);
			}
			map.put("msg", msg);
			return "/login";

		}
		
		return "/index";
	}

	@RequestMapping("/403")
	public String unauthorizedRole() {
		String msg = "------没有权限-------";
		LOG.info(msg);
		return "403";
	}

}
