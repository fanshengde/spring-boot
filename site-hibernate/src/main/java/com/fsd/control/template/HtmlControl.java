package com.fsd.control.template;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fsd.domain.UserInfo;

@Controller
public class HtmlControl {
	
	@RequestMapping(value="thRegister", method=RequestMethod.GET)
	public String addUserPortal(Model model) {
		model.addAttribute("userInfo", new UserInfo());
		return "register";
	}
}
