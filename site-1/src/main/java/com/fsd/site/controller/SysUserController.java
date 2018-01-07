package com.fsd.site.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fsd.site.domain.SysUser;

@Controller
@RequestMapping("sysUser")
public class SysUserController {
	@RequestMapping(value = "index", method=RequestMethod.GET)
	public String index(Model model) {
		List<SysUser> sysUserList  = new ArrayList<SysUser>();
		SysUser u1 = new SysUser(11L, "AAAAA", "123456");
        SysUser u2 = new SysUser(22L, "BBBBB", "123456");
        SysUser u3 = new SysUser(33L, "CCCCC", "123456");
        sysUserList.add(u1);
        sysUserList.add(u2);
        sysUserList.add(u3);
        
        model.addAttribute("sysUserList",sysUserList);
        model.addAttribute("message",sysUserList.hashCode());
        
        return "SysUser/list";
	}
}
