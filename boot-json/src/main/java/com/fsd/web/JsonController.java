package com.fsd.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsd.entity.User;

@Controller
@RequestMapping("/user")
public class JsonController {

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap modelMap) {
		modelMap.put("msg", "SpringBoot Ajax demo");
		return "index";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/data", method = RequestMethod.POST)
	@ResponseBody
	public List<User> data() {
		List<User> list = new ArrayList<User>();

		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setId((i + 1) + "");
			user.setName("springboot" + i);
			user.setSex("male");
			user.setAge((i + 1) + "");
			user.setRole("developer");

			list.add(user);
		}

		return list;
	}
}
