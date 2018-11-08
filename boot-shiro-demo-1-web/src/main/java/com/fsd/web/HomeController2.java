package com.fsd.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController2 {
	static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/ui/login", method = RequestMethod.GET)
	@ResponseBody
	public String getUserByid() {
		System.out.println("hello user 111");
		
		Object entity = new Object();
		if (entity == null) {
			return "没有任何数据";
		}
		return entity.toString();
	}

}
