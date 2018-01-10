package com.fsd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/users")
@RestController
public class UserInfoController {
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object list(HttpServletRequest request) {
		return "Get all User";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object detail(@PathVariable Integer sid) {
		return "get a user";
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Object create(HttpServletRequest request) {
		return "post a user";
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Object update(HttpServletRequest request) {
		return "put a user";
	}
}
