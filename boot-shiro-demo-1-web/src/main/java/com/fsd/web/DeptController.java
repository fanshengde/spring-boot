package com.fsd.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.entity.SysDept;
import com.fsd.entity.repository.SysDeptRepository;
import com.fsd.web.utils.JsonUtil;


@RestController
public class DeptController {
	static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	SysDeptRepository sysDeptRepository;
	
	@RequestMapping(value = "/ui/dept", method = RequestMethod.GET)
	@ResponseBody
	public String getUserByid() {
		System.out.println("hello user 111");
		
		Iterable<SysDept> sysDepts = sysDeptRepository.findAll();
//		model.addAttribute("sysPermissions", sysPermissions);
		
		if (sysDepts == null) {
			return "没有任何数据";
		}
		
		return JsonUtil.getInstance().parseList(sysDepts);
	}

}
