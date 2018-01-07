package com.fsd.site.htmlHandler;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.site.datasource.mysql.bean.Author;

@Controller
public class ThymeleafHandler {
	@RequestMapping(value ="/addUserController", method = RequestMethod.GET)
	public String addUser(Model model) {
		model.addAttribute("obj", new Author());
		return "/addUser";
	}
}
