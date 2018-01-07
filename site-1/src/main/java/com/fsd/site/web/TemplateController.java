package com.fsd.site.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller

public class TemplateController {
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String index(Map<String, Object> map) {
		map.put("hello","from TemplateController.helloHtml");
		return "/helloHtml";
	}
	
}
