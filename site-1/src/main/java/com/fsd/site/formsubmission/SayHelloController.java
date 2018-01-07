package com.fsd.site.formsubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SayHelloController {
	
	@RequestMapping(value = "/sayhello", method = RequestMethod.GET)
	public String sayHelloForm(Model model) {
		model.addAttribute("helloMessage", new HelloMessage());
		return "sayHello";
		
	}
	
	@RequestMapping(value = "/sayhello", method = RequestMethod.POST)
	public String sayHello(@ModelAttribute HelloMessage helloMessage, Model model) {
		model.addAttribute("helloMessage", helloMessage);
		return "message";
	}
}
