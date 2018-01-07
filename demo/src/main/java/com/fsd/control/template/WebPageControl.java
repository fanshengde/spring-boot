package com.fsd.control.template;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fsd.domain.SysUser;

@Controller
public class WebPageControl {
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("sysUser", new SysUser());
		return "th_login";
	}
}
